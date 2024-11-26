package com.embarkapps.snoozeloo.alarms.presentation.alarmlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.snoozeloo.alarms.data.model.toAlarm
import com.embarkapps.snoozeloo.alarms.data.model.toAlarmEntity
import com.embarkapps.snoozeloo.alarms.domain.alarmscheduler.AlarmScheduler
import com.embarkapps.snoozeloo.alarms.domain.repository.AlarmRepository
import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.model.toAlarm
import com.embarkapps.snoozeloo.alarms.presentation.model.toAlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.model.toFormattedTime
import com.embarkapps.snoozeloo.core.domain.Constants
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    companion object {
        const val TAG = "AlarmListViewModel"
    }

    init {
        loadAlarms()
    }

    private val _state = MutableStateFlow(AlarmListState())
    val state = _state
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
                    _state.update { alarmEntities ->
                        alarmEntities.copy(
                            alarms = alarms.map {
                                it.toAlarm().toAlarmUi()
                            }
                        )
                    }
                }
        }
    }

    private fun updateAlarmsList(alarmUi: AlarmUi) {
        val newList = _state.value.alarms.toMutableList()
        val newAlarm = newList.find { listAlarm -> alarmUi.id == listAlarm.id }
        val updatedAlarm = newAlarm?.copy(isEnabled = !newAlarm.isEnabled)
        for (i in newList.indices) {
            if (newList[i] == alarmUi) {
                newList[i] = updatedAlarm!!
            }
        }

        _state.update { state ->
            state.copy(
                alarms = newList
            )
        }

        viewModelScope.launch {
            repository.upsertAll(updatedAlarm!!.toAlarm().toAlarmEntity())
        }

    }

    fun eventHandler(alarmListUiEvent: AlarmListUiEvent) {
        viewModelScope.launch {
            when (alarmListUiEvent) {
                AlarmListUiEvent.OnAddAlarmClicked -> {
                    _state.update {
                        it.copy(
                            selectedAlarm = AlarmUi(
                                title = "",
                                hour = 0.toFormattedTime(Constants.HOUR),
                                minute = 0.toFormattedTime(Constants.MINUTE),
                                isEnabled = true
                            ),
                            isValid = false
                        )
                    }
                    navigator.navigate(Destination.AlarmDetailScreen)
                }

                is AlarmListUiEvent.OnAlarmClicked -> {
                    _state.update {
                        it.copy(selectedAlarm = alarmListUiEvent.alarmUi)
                    }
                    checkValidity()
                    navigator.navigate(Destination.AlarmDetailScreen)
                }

                is AlarmListUiEvent.OnAlarmEnableToggle -> {
                    updateAlarmsList(alarmListUiEvent.alarmUi)
                }

                is AlarmListUiEvent.OnAlarmSaved -> {
                    saveAlarm(alarmListUiEvent.alarm)
                }

                AlarmListUiEvent.OnCloseClicked -> {
                    navigator.navigateUp()
                }

                is AlarmListUiEvent.OnTitleChanged -> {
                    _state.update {
                        it.copy(
                            selectedAlarm = it.selectedAlarm?.copy(
                                title = alarmListUiEvent.title
                            )
                        )
                    }
                    checkValidity()
                }

                is AlarmListUiEvent.OnTimeSelected -> {
                    _state.update {
                        it.copy(
                            selectedAlarm = it.selectedAlarm?.copy(
                                hour = alarmListUiEvent.hour.toInt()
                                    .toFormattedTime(Constants.HOUR),
                                minute = alarmListUiEvent.minute.toInt()
                                    .toFormattedTime(Constants.MINUTE)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun checkValidity() {
        if (!_state.value.selectedAlarm?.title.isNullOrEmpty()) {
            _state.update {
                it.copy(
                    isValid = true
                )
            }
        } else {
            _state.update {
                it.copy(
                    isValid = false
                )
            }
        }
    }

    private fun saveAlarm(alarmUi: AlarmUi) {
        viewModelScope.launch {
            repository.upsertAll(
                alarmUi.copy(
                    id = _state.value.selectedAlarm?.id ?: 0L
                ).toAlarm().toAlarmEntity()
            )
            loadAlarms()
            alarmScheduler.schedule(alarmUi.toAlarm())
            navigator.navigateUp()
        }
    }

}