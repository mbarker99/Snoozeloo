package com.embarkapps.snoozeloo.alarms.domain.repository

import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    suspend fun getAllAlarms(): Flow<List<AlarmEntity>>
    suspend fun upsertAll(vararg alarms: AlarmEntity)
}