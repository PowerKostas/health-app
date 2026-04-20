package com.example.gohealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gohealth.data.daos.CharacteristicsDao
import com.example.gohealth.data.daos.SettingsDao
import com.example.gohealth.data.daos.TrackingsDao
import com.example.gohealth.data.entities.Characteristics
import com.example.gohealth.data.entities.Settings
import com.example.gohealth.data.entities.Trackings

@Database(entities = [Settings::class, Characteristics::class, Trackings::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characteristicsDao(): CharacteristicsDao
    abstract fun settingsDao(): SettingsDao
    abstract fun trackingsDao(): TrackingsDao
}
