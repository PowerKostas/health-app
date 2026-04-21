package com.example.gohealth.data

import androidx.room.TypeConverter

// I have to use this trick for the tracking table because SQLite cannot store lists in a column
class Converters {
    // Converts the list to a comma-separated string: "1,2,3"
    @TypeConverter
    fun fromIntList(list: List<Int?>?): String? {
        return list?.joinToString(", ")
    }

    // Converts the comma-separated string back to a list of Ints
    @TypeConverter
    fun toIntList(data: String?): List<Int?> {
        if (data.isNullOrBlank()) return emptyList()
        return data.split(", ").mapNotNull { it.toIntOrNull() }
    }
}
