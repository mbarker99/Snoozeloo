package com.embarkapps.snoozeloo.alarms.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import com.embarkapps.snoozeloo.core.domain.Constants

@Dao
interface AlarmDao {
    @Query("SELECT * FROM ${Constants.ALARM_TABLE_NAME}")
    suspend fun getAllAlarms(): List<AlarmEntity>

    @Upsert
    suspend fun insertAll(vararg alarmEntities: AlarmEntity)

    @Delete
    suspend fun delete(alarmEntity: AlarmEntity)
}