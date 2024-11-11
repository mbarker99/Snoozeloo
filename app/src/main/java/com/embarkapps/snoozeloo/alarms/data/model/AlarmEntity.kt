package com.embarkapps.snoozeloo.alarms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.core.domain.Constants
import java.time.LocalDateTime

@Entity(tableName = Constants.ALARM_TABLE_NAME)
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val isEnabled: Boolean,
    val hour: Int,
    val minute: Int
) {
    fun toAlarm(): Alarm {
        return Alarm(
            id = id,
            title = title,
            time = LocalDateTime.now(),
            isEnabled = isEnabled
        )
    }
}
