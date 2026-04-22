package com.kostas.gohealth.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kostas.gohealth.data.entities.Settings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings")
    fun getAll(): Flow<List<Settings>>

    @Insert
    suspend fun insert(settings: Settings)

    @Update
    suspend fun update(settings: Settings)
}
