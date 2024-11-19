package com.embarkapps.snoozeloo.alarms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.domain.model.toFormattedTime
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
    val isAm = !(time.hour >= 12 && time.hour != 0)
    return Alarm(
        title = title,
        hour = time.hour.toFormattedTime("hour"),
        minute = time.minute.toFormattedTime("minute"),
        isAm = isAm,
        isEnabled = isEnabled,
        id = id
    )
}