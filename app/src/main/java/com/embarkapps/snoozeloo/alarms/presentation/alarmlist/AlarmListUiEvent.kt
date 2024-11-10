package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

sealed interface AlarmListUiEvent {
    data object OnAddAlarmClicked : AlarmListUiEvent
    data class OnAlarmEnableToggle(val alarm: Alarm) : AlarmListUiEvent
}