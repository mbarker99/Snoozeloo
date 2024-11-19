package com.embarkapps.snoozeloo.alarms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.core.domain.Constants
import java.time.LocalDateTime

@Entity(tableName = Constants.ALARM_TABLE_NAME)
data class AlarmEntity(
    val title: String,
    val time: LocalDateTime,
    val isEnabled: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)

fun AlarmEntity.toAlarm(): Alarm {
    return Alarm(
        title = title,
        time = time,
        isEnabled = isEnabled,
        id = id
    )
}

fun Alarm.toAlarmEntity(): AlarmEntity {
    return AlarmEntity(
        title = title,
        time = time,
        isEnabled = isEnabled,
        id = id
    )
}