package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi

data class AlarmListState(
    val isLoading: Boolean = false,
    val alarms: List<AlarmUi> = emptyList(),
    val selectedAlarm: AlarmUi? = null
)
