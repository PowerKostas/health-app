package com.kostas.gohealth.services

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.kostas.gohealth.data.DatabaseProvider
import com.kostas.gohealth.helpers.calculateCaloriesGoal
import com.kostas.gohealth.helpers.calculatePushUpsGoal
import com.kostas.gohealth.helpers.calculateWaterGoal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDate

// If it's a new day, it resets the normal fields in the trackings table, also it increases the unsynced fields, in case there is no network
// to send the data at the remote database, this function is chained with the leaderboard sync one to ensure that the reset executes
// first, this function is called from MainActivity
class ResetTrackingsWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val database = DatabaseProvider.getDatabase(applicationContext)
                val trackingsDao = database.trackingsDao()
                val userTrackings = trackingsDao.getAll().first().firstOrNull()
                val settingsDao = database.settingsDao()
                val userSettings = database.settingsDao().getAll().first().firstOrNull()
                val userCharacteristics = database.characteristicsDao().getAll().first().firstOrNull()

                // Triggers on a fresh install
                if (userTrackings == null || userSettings == null || userCharacteristics == null) {
                    return@withContext Result.retry()
                }

                // If it has already reset today, it doesn't reset again
                if (LocalDate.now().toString() <= userSettings.lastSavedDate) {
                    return@withContext Result.success()
                }

                val waterGoal = calculateWaterGoal(userCharacteristics)
                val caloriesGoal = calculateCaloriesGoal(userCharacteristics)
                val pushUpsGoal = calculatePushUpsGoal(userCharacteristics)

                val waterGoalCompleted = if (userTrackings.waterProgress.sum() >= waterGoal) 1 else 0
                val caloriesGoalCompleted = if (userTrackings.caloriesProgress.sum() >= caloriesGoal) 1 else 0
                val pushUpsGoalCompleted = if (userTrackings.pushUpsProgress.sum() >= pushUpsGoal) 1 else 0
                val totalSteps = userTrackings.stepsProgress

                val updateUserTrackings = userTrackings.copy(
                    unsyncedWaterGoalsCompleted = userTrackings.unsyncedWaterGoalsCompleted + waterGoalCompleted,
                    unsyncedCaloriesGoalsCompleted = userTrackings.unsyncedCaloriesGoalsCompleted + caloriesGoalCompleted,
                    unsyncedPushUpsGoalsCompleted = userTrackings.unsyncedPushUpsGoalsCompleted + pushUpsGoalCompleted,
                    unsyncedTotalSteps = userTrackings.unsyncedTotalSteps + totalSteps,

                    waterProgress = emptyList(),
                    caloriesProgress = emptyList(),
                    pushUpsProgress = emptyList(),
                    stepsProgress = 0
                )

                trackingsDao.update(updateUserTrackings)

                // Every new request replaces the old one, since all the data needed is added up in the local database
                val workRequest = OneTimeWorkRequestBuilder<LeaderboardsSyncWorker>()
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )

                    .build()

                WorkManager.getInstance(applicationContext).enqueueUniqueWork(
                    "leaderboard_sync",
                    androidx.work.ExistingWorkPolicy.REPLACE,
                    workRequest
                )

                val updateUserSettings = userSettings.copy(lastSavedDate = LocalDate.now().toString())
                settingsDao.update(updateUserSettings)

                Result.success()
            }

            catch (e: Exception) {
                e.printStackTrace()
                Result.retry()
            }
        }
    }
}
