package com.embarkapps.snoozeloo.alarms.data.db

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun timeToString(time: LocalDateTime): String {
        return time.toString()
    }

    @TypeConverter
    fun stringToTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}