package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

data class AlarmListState(
    val isLoading: Boolean,
    val alarms: List<Alarm>
)
