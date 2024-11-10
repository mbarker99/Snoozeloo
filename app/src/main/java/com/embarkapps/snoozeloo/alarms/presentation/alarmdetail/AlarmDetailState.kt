package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

data class AlarmDetailState(
    val isLoading: Boolean,
    val alarm: Alarm,
    val isExtended: Boolean = false,
    val isValid: Boolean = false
)