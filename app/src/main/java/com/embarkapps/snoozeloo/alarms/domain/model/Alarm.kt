package com.embarkapps.snoozeloo.alarms.domain.model

import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import java.time.LocalDateTime

data class Alarm(
    val title: String,
    val hour: FormattedTime,
    val minute: FormattedTime,
    val isAm: Boolean,
    val isEnabled: Boolean,
    val id: Long = 0L,
)

data class FormattedTime(
    val type: String,
    val value: Int,
    val formattedValue: String
)

fun Int.toFormattedTime(type: String): FormattedTime {
    var formattedValue = ""
    when (type) {
        "hour" -> {
            formattedValue = when (this) {
                0 -> "12"
                in 1..9 -> "0$this"
                in 10..12 -> this.toString()
                in 13..21 -> "0${this - 12}"
                else -> (this - 12).toString()
            }

        }

        "minute" -> {
            formattedValue =
                if (this < 10)
                    "0$this"
                else
                    this.toString()
        }

        else -> {}
    }
    return FormattedTime(
        type = type,
        value = this,
        formattedValue = formattedValue
    )
}

fun Alarm.toAlarmEntity(): AlarmEntity {
    val currentTime = LocalDateTime.now()
    val time = LocalDateTime.of(
        currentTime.year,
        currentTime.month,
        currentTime.dayOfMonth,
        hour.value,
        minute.value
    )
    return AlarmEntity(
        title = title,
        isEnabled = isEnabled,
        time = time,
        id = id
    )
}
