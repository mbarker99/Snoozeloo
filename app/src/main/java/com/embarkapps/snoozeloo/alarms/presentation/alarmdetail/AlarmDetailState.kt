package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

data class AlarmDetailState(
    val isLoading: Boolean = false,
    val hour: Int = 0,
    val minute: Int = 0,
    val title: String = "",
    val isExtended: Boolean = false,
    val isValid: Boolean = false
)