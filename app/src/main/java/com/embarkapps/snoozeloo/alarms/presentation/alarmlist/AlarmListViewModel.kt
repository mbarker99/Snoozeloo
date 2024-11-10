package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    companion object {
        const val TAG = "AlarmListViewModel"
    }

    private val _state = MutableStateFlow(AlarmListState())
    val state: StateFlow<AlarmListState> = _state

    fun eventHandler(alarmListUiEvent: AlarmListUiEvent) {
        viewModelScope.launch {
            when (alarmListUiEvent) {
                AlarmListUiEvent.OnAddAlarmClicked -> {
                    Log.d(TAG, "onAddAlarmClicked")
                    navigator.navigate(Destination.AlarmDetailScreen)
                }

                is AlarmListUiEvent.OnAlarmEnableToggle -> {
                    val toggleState =
                        if (alarmListUiEvent.alarm.isEnabled) "TOGGLE OFF" else "TOGGLE ON"
                    Log.d(TAG, "onAlarmEnableToggle: $toggleState")

                }
            }
        }
    }
}