package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.BluePrimary
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.Gray
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.GraySurface
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    alarmUi: AlarmUi,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    val timePickerState = rememberTimePickerState(
        initialHour = alarmUi.hour.value,
        initialMinute = alarmUi.minute.value,
        is24Hour = false
    )

    val timePickerColors = TimePickerDefaults.colors().copy(
        clockDialColor = Gray,
        selectorColor = BluePrimary,
        containerColor = Gray,
        periodSelectorBorderColor = Color.White,
        clockDialSelectedContentColor = Color.White,
        clockDialUnselectedContentColor = Color.Black,
        periodSelectorSelectedContainerColor = BluePrimary,
        periodSelectorUnselectedContainerColor = Gray,
        periodSelectorSelectedContentColor = Color.White,
        periodSelectorUnselectedContentColor = Color.Black,
        timeSelectorSelectedContainerColor = BluePrimary,
        timeSelectorUnselectedContainerColor = Gray,
        timeSelectorSelectedContentColor = Color.White,
        timeSelectorUnselectedContentColor = Color.Black,
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(
                    timePickerState.hour.toString(),
                    timePickerState.minute.toString()
                )
            }) {
                Text("OK")
            }
        },
        text = {
            TimePicker(
                state = timePickerState,
                colors = timePickerColors
            )
        },
        containerColor = GraySurface,
    )
}

@Preview
@Composable
fun TimePickerDialogPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        TimePickerDialog(
            alarmUi = previewAlarm,
            onDismiss = { },
            onConfirm = { _, _ -> }
        )
    }
}