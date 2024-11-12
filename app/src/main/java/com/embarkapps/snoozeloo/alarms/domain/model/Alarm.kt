package com.embarkapps.snoozeloo.alarms.domain.model

import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import java.time.LocalDateTime

data class Alarm(
    val title: String,
    val hour: String,
    val minute: String,
    val isEnabled: Boolean,
    val id: Long = 0L,
)

fun Alarm.toAlarmEntity(): AlarmEntity {
    val currentTime = LocalDateTime.now()
    val time = LocalDateTime.of(
        currentTime.year,
        currentTime.month,
        currentTime.dayOfMonth,
        hour.toInt(),
        minute.toInt()
    )
    return AlarmEntity(
        title = title,
        isEnabled = isEnabled,
        time = time,
        id = id
    )
}
