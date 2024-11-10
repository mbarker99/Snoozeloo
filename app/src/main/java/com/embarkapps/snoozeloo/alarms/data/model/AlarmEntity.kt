package com.embarkapps.snoozeloo.alarms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.embarkapps.snoozeloo.core.domain.Constants

@Entity(tableName = Constants.ALARM_TABLE_NAME)
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val isEnabled: Boolean,
    //val time: LocalDateTime,
)
