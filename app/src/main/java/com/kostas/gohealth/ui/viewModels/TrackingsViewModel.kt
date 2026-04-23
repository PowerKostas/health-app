package com.kostas.gohealth.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kostas.gohealth.data.DatabaseProvider
import com.kostas.gohealth.data.daos.SettingsDao
import com.kostas.gohealth.data.daos.TrackingsDao
import com.kostas.gohealth.data.entities.Trackings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class TrackingsViewModel(private val trackingsDao: TrackingsDao, private val settingsDao: SettingsDao) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val database = DatabaseProvider.getDatabase(application)
                return TrackingsViewModel(database.trackingsDao(), settingsDao = database.settingsDao()) as T
            }
        }
    }

    val trackings: StateFlow<List<Trackings>> = trackingsDao.getAll().stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun initializeUserTrackings(userId: Int) {
        viewModelScope.launch {
            if (trackingsDao.getAll().first().isEmpty()) {
                val defaultTrackings = Trackings(
                    userId = userId,
                    waterProgress = emptyList(),
                    caloriesProgress = emptyList(),
                    pushUpsProgress = emptyList(),
                    stepsProgress = emptyList()
                )

                trackingsDao.insert(defaultTrackings)
            }
        }
    }

    fun updateUserTrackings(trackings: Trackings) {
        viewModelScope.launch {
            trackingsDao.update(trackings)
        }
    }

    // Resets the tracking table if today is after the last reset date and a specific time has passed, the second part is just for
    // testing, since I just want it to reset at midnight
    fun resetUserTrackings() {
        viewModelScope.launch {
            val userTrackings = trackings.value.firstOrNull()
            val userSettings = settingsDao.getAll().first().firstOrNull()

            if (userTrackings != null && userSettings != null) {
                if (LocalDate.now() > userSettings.lastResetDate && LocalTime.now() >= LocalTime.of(0, 0)) {
                    val resetTrackings = userTrackings.copy(
                        waterProgress = emptyList(),
                        caloriesProgress = emptyList(),
                        pushUpsProgress = emptyList(),
                        stepsProgress = emptyList()
                    )

                    trackingsDao.update(resetTrackings)
                    settingsDao.update(userSettings.copy(lastResetDate = LocalDate.now()))
                }
            }
        }
    }

    // Every 10 new steps, it updates stepsProgress and lastSavedSteps. The step counter in Android counts steps since the last reset, this
    // is why we subtract the lastSavedSteps from the totalSteps to get the new steps. New steps go into stepsProgress, stepsProgress gets
    // reset every midnight and lastSavedSteps gets the value of totalSteps.
    fun updateStepsProgress(totalSteps: Int) {
        viewModelScope.launch {
            val userTrackings = trackings.value.firstOrNull()
            val userSettings = settingsDao.getAll().first().firstOrNull()

            if (userTrackings != null && userSettings != null) {
                if (totalSteps - userSettings.lastSavedSteps >= 10) {
                    updateUserTrackings(userTrackings.copy(stepsProgress = userTrackings.stepsProgress + (totalSteps - userSettings.lastSavedSteps)))
                    settingsDao.update(userSettings.copy(lastSavedSteps = totalSteps))
                }
            }
        }
    }
}
