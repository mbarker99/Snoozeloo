package com.embarkapps.snoozeloo.alarms.data.repository

import com.embarkapps.snoozeloo.alarms.data.db.AlarmDatabase
import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import com.embarkapps.snoozeloo.alarms.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val db: AlarmDatabase
) : AlarmRepository {
    override suspend fun getAllAlarms(): Flow<List<AlarmEntity>> = flow {
        emit(db.alarmDao().getAllAlarms())
    }

    override suspend fun insertAll(vararg alarms: AlarmEntity) = db.alarmDao().insertAll(*alarms)
}