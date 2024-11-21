package com.embarkapps.snoozeloo.alarms.presentation.model

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.core.domain.Constants
import java.time.LocalDateTime

data class AlarmUi(
    var title: String,
    var hour: FormattedTime,
    var minute: FormattedTime,
    var isEnabled: Boolean,
    var id: Long = 0L,
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

fun Alarm.toAlarmUi(): AlarmUi {
    return AlarmUi(
        title = this.title,
        hour = this.time.hour.toFormattedTime(Constants.HOUR),
        minute = this.time.minute.toFormattedTime(Constants.MINUTE),
        isEnabled = this.isEnabled,
        id = this.id
    )
}

fun AlarmUi.toAlarm(): Alarm {
    val currentTime = LocalDateTime.now()
    val time = LocalDateTime.of(
        currentTime.year,
        currentTime.month,
        currentTime.dayOfMonth,
        hour.value,
        minute.value
    )
    return Alarm(
        title = title,
        isEnabled = isEnabled,
        time = time,
        id = id
    )
}

