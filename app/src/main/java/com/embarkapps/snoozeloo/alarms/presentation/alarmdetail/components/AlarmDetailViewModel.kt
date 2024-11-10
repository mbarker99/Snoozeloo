package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.lifecycle.ViewModel
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.AlarmDetailState
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlarmDetailViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(AlarmDetailState(alarm = previewAlarm))
    val state: StateFlow<AlarmDetailState> = _state
}