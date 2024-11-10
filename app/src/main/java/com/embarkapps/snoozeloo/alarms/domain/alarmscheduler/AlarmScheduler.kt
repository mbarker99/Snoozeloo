package com.embarkapps.snoozeloo.alarms.domain.alarmscheduler

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

interface AlarmScheduler {
    fun schedule(item: Alarm)
    fun cancel(item: Alarm)
}