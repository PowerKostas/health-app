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

// Every column will hold a list of all the additions of the user, breaks 1NF but ok
data class Trackings(
    @PrimaryKey val userId: Int,
    @ColumnInfo(name = "water_progress") val waterProgress: List<Int?>,
    @ColumnInfo(name = "calories_progress") val caloriesProgress: List<Int?>,
    @ColumnInfo(name = "push_ups_progress") val pushUpsProgress: List<Int?>,
    @ColumnInfo(name = "steps_progress") val stepsProgress: List<Int?>
)
