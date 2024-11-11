package com.embarkapps.snoozeloo.alarms.domain.model

import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import java.time.LocalDateTime

data class Alarm(
    val id: Int,
    val title: String,
    val time: LocalDateTime,
    val isEnabled: Boolean
) {
    fun toAlarmEntity(): AlarmEntity {
        return AlarmEntity(
            id = id,
            title = title,
            isEnabled = isEnabled,
            hour = time.hour,
            minute = time.minute
        )
    }
}
