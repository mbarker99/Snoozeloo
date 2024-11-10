package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(AlarmListState())
    val state: StateFlow<AlarmListState> = _state
}