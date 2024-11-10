package com.embarkapps.snoozeloo.alarms.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity

@Database(entities = [AlarmEntity::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}