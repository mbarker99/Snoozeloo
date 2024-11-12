package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm

sealed interface AlarmListUiEvent {
    // AlarmListScreen
    data object OnAddAlarmClicked : AlarmListUiEvent
    data class OnAlarmEnableToggle(val alarm: Alarm) : AlarmListUiEvent

    // AlarmDetailScreen
    data object OnCloseClicked : AlarmListUiEvent
    data class OnTitleChanged(val title: String) : AlarmListUiEvent
    data class OnHourChanged(val hour: String) : AlarmListUiEvent
    data class OnMinuteChanged(val minute: String) : AlarmListUiEvent
    data class OnAlarmSaved(val alarm: AlarmEntity) : AlarmListUiEvent
}