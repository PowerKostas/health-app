package com.kostas.gohealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kostas.gohealth.data.daos.CharacteristicsDao
import com.kostas.gohealth.data.daos.SettingsDao
import com.kostas.gohealth.data.daos.TrackingsDao
import com.kostas.gohealth.data.entities.Characteristics
import com.kostas.gohealth.data.entities.Settings
import com.kostas.gohealth.data.entities.Trackings

@Database(entities = [Settings::class, Characteristics::class, Trackings::class], version = 1)
@TypeConverters(Converters::class) // Automatically runs the converters, I can just use the lists/dates as lists/dates in code now
abstract class AppDatabase : RoomDatabase() {
    abstract fun characteristicsDao(): CharacteristicsDao
    abstract fun settingsDao(): SettingsDao
    abstract fun trackingsDao(): TrackingsDao
}
