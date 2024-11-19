package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.snoozeloo.alarms.data.model.toAlarm
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.domain.model.toAlarmEntity
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
        val newAlarm = newList.find { listAlarm -> alarm.id == listAlarm.id }
        val updatedAlarm = newAlarm?.copy(isEnabled = !newAlarm.isEnabled)
        for (i in newList.indices) {
            if (newList[i] == alarm) {
                newList[i] = updatedAlarm!!
                Log.d("AlarmListViewModel", "isEnabled: ${newList[i].isEnabled}")
            }
        }

        _listState.update { state ->
            state.copy(
                alarms = newList
            )
        }

        viewModelScope.launch {
            repository.upsertAll(updatedAlarm!!.toAlarmEntity())
        }

    }

    fun eventHandler(alarmListUiEvent: AlarmListUiEvent) {
        viewModelScope.launch {
            when (alarmListUiEvent) {
                AlarmListUiEvent.OnAddAlarmClicked -> {
                    Log.d(TAG, "onAddAlarmClicked")
                    navigator.navigate(Destination.AlarmDetailScreen)
                }

                is AlarmListUiEvent.OnAlarmClicked -> {
                    _detailState.update {
                        println(
                            it.copy(
                                selectedAlarm = alarmListUiEvent.alarm,
                                hour = alarmListUiEvent.alarm.hour.formattedValue,
                                minute = alarmListUiEvent.alarm.minute.formattedValue,
                                title = alarmListUiEvent.alarm.title,
                                isExtended = false,
                            ).toString()
                        )
                        it.copy(
                            selectedAlarm = alarmListUiEvent.alarm,
                            hour = alarmListUiEvent.alarm.hour.formattedValue,
                            minute = alarmListUiEvent.alarm.minute.formattedValue,
                            title = alarmListUiEvent.alarm.title,
                            isExtended = false,
                        )
                    }
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

                is AlarmListUiEvent.OnTimeSelected -> {
                    _detailState.update {
                        it.copy(
                            hour = alarmListUiEvent.hour,
                            minute = alarmListUiEvent.minute
                        )
                    }
                }
            }
        }
    }

    // TODO : form validation - app crashes when title is inputted before time
    private fun checkValidity() {
        val validHour = if (_detailState.value.hour == "") "0" else _detailState.value.hour
        val validMinute = if (_detailState.value.minute == "") "0" else _detailState.value.minute
        if (_detailState.value.title.isNotEmpty()
            && validHour.toInt() <= 24
            && validMinute.toInt() <= 59
        ) {
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

    private fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            if (alarm.id == 0L) {
                repository.upsertAll(alarm.toAlarmEntity())
            } else {
                repository.upsertAll(
                    alarm.copy(
                        id = _detailState.value.selectedAlarm?.id ?: 0L
                    ).toAlarmEntity()
                )
            }
            navigator.navigateUp()
        }
    }

}