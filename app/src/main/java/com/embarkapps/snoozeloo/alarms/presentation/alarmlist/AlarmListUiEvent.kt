package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi

sealed interface AlarmListUiEvent {
    // AlarmListScreen
    data object OnAddAlarmClicked : AlarmListUiEvent
    data class OnAlarmClicked(val alarmUi: AlarmUi) : AlarmListUiEvent
    data class OnAlarmEnableToggle(val alarmUi: AlarmUi) : AlarmListUiEvent

    // AlarmDetailScreen
    data object OnCloseClicked : AlarmListUiEvent
    data class OnTitleChanged(val title: String) : AlarmListUiEvent
    data class OnAlarmSaved(val alarm: AlarmUi) : AlarmListUiEvent
    data class OnTimeSelected(val hour: String, val minute: String) : AlarmListUiEvent
}