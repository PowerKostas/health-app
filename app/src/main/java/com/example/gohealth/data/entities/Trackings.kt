package com.example.gohealth.data.entities

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

data class Trackings(
    @PrimaryKey val userId: Int,
    @ColumnInfo(name = "water_progress") val waterProgress: Float?,
    @ColumnInfo(name = "calories_progress") val caloriesProgress: Float?,
    @ColumnInfo(name = "push_ups_progress") val pushUpsProgress: Float?,
    @ColumnInfo(name = "steps_progress") val stepsProgress: Float?
)
