package com.embarkapps.snoozeloo.alarms.data.model

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.core.domain.Constants
import java.time.LocalDateTime

@Entity(tableName = Constants.ALARM_TABLE_NAME)
data class AlarmEntity(
    val title: String,
    val isEnabled: Boolean,
    val hour: Int,
    val minute: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    fun toAlarm(): Alarm {
        val currentTime = LocalDateTime.now()
        val time = LocalDateTime.of(
            currentTime.year,
            currentTime.month,
            currentTime.dayOfMonth,
            hour,
            minute
        )
        Log.d("AlarmEntity", "time: $time")
        return Alarm(
            id = id,
            title = title,
            time = time,
            isEnabled = isEnabled
        )
    }
}
