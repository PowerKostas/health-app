package com.kostas.gohealth.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "trackings",
    foreignKeys = [
        ForeignKey(
            entity = Settings::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

// Every normal column will hold a list of all the additions of the user, breaks 1NF but ok. Every unsynced column will hold an int that will
// increase every midnight, if the user has no network
data class Trackings(
    @PrimaryKey val userId: Int,
    @ColumnInfo(name = "water_progress") val waterProgress: List<Int> = emptyList(),
    @ColumnInfo(name = "calories_progress") val caloriesProgress: List<Int> = emptyList(),
    @ColumnInfo(name = "push_ups_progress") val pushUpsProgress: List<Int> = emptyList(),
    @ColumnInfo(name = "steps_progress") val stepsProgress: Int = 0,
    @ColumnInfo(name = "unsynced_water_goals_completed") val unsyncedWaterGoalsCompleted: Int = 0,
    @ColumnInfo(name = "unsynced_calories_goals_completed") val unsyncedCaloriesGoalsCompleted: Int = 0,
    @ColumnInfo(name = "unsynced_push_ups_goals_completed") val unsyncedPushUpsGoalsCompleted: Int = 0,
    @ColumnInfo(name = "unsynced_total_steps") val unsyncedTotalSteps: Int = 0
)
