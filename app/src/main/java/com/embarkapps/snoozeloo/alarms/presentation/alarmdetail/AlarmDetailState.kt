package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

data class AlarmDetailState(
    val isLoading: Boolean = false,
    val selectedAlarm: Alarm? = null,
    val hour: String = "00",
    val minute: String = "00",
    val title: String = "",
    val isExtended: Boolean = false,
    val isValid: Boolean = false
)