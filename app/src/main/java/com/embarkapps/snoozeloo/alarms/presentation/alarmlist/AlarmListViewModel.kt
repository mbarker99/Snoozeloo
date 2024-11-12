package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.domain.repository.AlarmRepository
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.AlarmDetailState
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: AlarmRepository
) : ViewModel() {

    companion object {
        const val TAG = "AlarmListViewModel"
    }

    init {
        loadAlarms()
    }

    private val _listState = MutableStateFlow(AlarmListState())
    val listState = _listState
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AlarmListState()
        )

    private fun loadAlarms() {
        viewModelScope.launch {
            repository.getAllAlarms()
                .flowOn(Dispatchers.IO)
                .collect { alarms ->
                    _listState.update { alarmEntities ->
                        alarmEntities.copy(
                            alarms = alarms.map {
                                it.toAlarm()
                            }
                        )
                    }
                }
        }
    }

    private val _detailState = MutableStateFlow(AlarmDetailState())
    val detailState: StateFlow<AlarmDetailState> = _detailState

    private fun updateAlarmsList(alarm: Alarm) {
        val newList = _listState.value.alarms.toMutableList()
        for (i in newList.indices) {
            if (newList[i] == alarm) {
                newList[i] = Alarm(
                    id = alarm.id,
                    title = alarm.title,
                    time = alarm.time,
                    isEnabled = !alarm.isEnabled
                )
                Log.d("AlarmListViewModel", "isEnabled: ${newList[i].isEnabled}")
            }
        }

        _listState.update { state ->
            state.copy(
                alarms = newList
            )
        }
    }

    fun eventHandler(alarmListUiEvent: AlarmListUiEvent) {
        viewModelScope.launch {
            when (alarmListUiEvent) {
                AlarmListUiEvent.OnAddAlarmClicked -> {
                    Log.d(TAG, "onAddAlarmClicked")
                    navigator.navigate(Destination.AlarmDetailScreen)
                }

                is AlarmListUiEvent.OnAlarmEnableToggle -> {
                    updateAlarmsList(alarmListUiEvent.alarm)
                }

                is AlarmListUiEvent.OnAlarmSaved -> {
                    saveAlarm(alarmListUiEvent.alarm)
                }

                AlarmListUiEvent.OnCloseClicked -> {
                    navigator.navigateUp()
                }

                is AlarmListUiEvent.OnTitleChanged -> {
                    _detailState.update {
                        it.copy(
                            title = alarmListUiEvent.title
                        )
                    }
                    checkValidity()
                }

                is AlarmListUiEvent.OnHourChanged -> {
                    _detailState.update {
                        it.copy(
                            hour = alarmListUiEvent.hour.toInt()
                        )
                    }
                    checkValidity()
                }

                is AlarmListUiEvent.OnMinuteChanged -> {
                    _detailState.update {
                        it.copy(
                            minute = alarmListUiEvent.minute.toInt()
                        )
                    }
                    checkValidity()
                }
            }
        }
    }

    private fun checkValidity() {
        if (_detailState.value.title.isNotEmpty()) {
            _detailState.update {
                it.copy(
                    isValid = true
                )
            }
        } else {
            _detailState.update {
                it.copy(
                    isValid = false
                )
            }
        }
    }

    private fun saveAlarm(alarm: AlarmEntity) {
        viewModelScope.launch {
            repository.insertAll(alarm)
            navigator.navigateUp()
        }
    }

}