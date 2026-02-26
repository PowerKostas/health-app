package com.example.gohealth.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// An entity represents a table within the database
@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "profile_picture_string") val profilePictureString: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Float?,
    @ColumnInfo(name = "height") val height: Float?,
    @ColumnInfo(name = "weight") val weight: Float?,
    @ColumnInfo(name = "activity_level") val activityLevel: String,
    @ColumnInfo(name = "weight_goal") val weightGoal: String,
    @ColumnInfo(name = "appearance") val appearance: String
)
