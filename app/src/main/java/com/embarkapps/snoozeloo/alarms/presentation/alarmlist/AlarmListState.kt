package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

data class AlarmListState(
    val isLoading: Boolean = false,
    val alarms: List<Alarm> = emptyList(),
    val selectedAlarm: Alarm? = null
)
