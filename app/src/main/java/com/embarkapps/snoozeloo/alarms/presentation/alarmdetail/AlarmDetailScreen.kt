package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components.AlarmNameCard
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components.TimeCard
import com.embarkapps.snoozeloo.alarms.presentation.alarmdetail.components.TimePickerDialog
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListState
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListUiEvent
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.model.toFormattedTime
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.BluePrimary
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily
import com.embarkapps.snoozeloo.core.domain.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDetailScreen(
    state: AlarmListState,
    onEvent: (AlarmListUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val openAlertDialog = remember { mutableStateOf(false) }

    val isExtended = false

    val saveButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = BluePrimary,
        disabledContentColor = Color.White
    )

    val cardColors = CardDefaults.cardColors().copy(
        containerColor = Color.White,
        contentColor = Color.Black,
        disabledContainerColor = Color.White,
        disabledContentColor = Color.Black
    )

    val sampleAlarm = AlarmUi(
        title = "",
        hour = 0.toFormattedTime(Constants.HOUR),
        minute = 0.toFormattedTime(Constants.MINUTE),
        isAm = true,
        isEnabled = true,
        id = 0
    )

    val alarm = state.selectedAlarm!!
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .padding(top = 32.dp)
    ) {
        // Close and Save buttons
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onEvent(AlarmListUiEvent.OnCloseClicked) },
                enabled = true,
                colors = saveButtonColors,
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Button(onClick = {
                onEvent(
                    AlarmListUiEvent.OnAlarmSaved(
                        AlarmUi(
                            title = alarm.title,
                            hour = alarm.hour,
                            minute = alarm.minute,
                            isAm = alarm.isAm,
                            isEnabled = true,
                        )
                    )
                )
            }, enabled = true, colors = saveButtonColors, content = {
                Text(
                    text = "Save",
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 19.5.sp,
                    textAlign = TextAlign.Center
                )
            }, modifier = Modifier.height(32.dp)
            )

        }


        TimeCard(
            hour = alarm.hour.formattedValue,
            minute = alarm.minute.formattedValue,
            onClick = { openAlertDialog.value = true }
        )


        Card(
            colors = cardColors,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            AlarmNameCard(
                title = alarm.title,
                onTitleChanged = { onEvent(AlarmListUiEvent.OnTitleChanged(it)) })
        }
        if (isExtended) {
            // TODO : add additional logic here
        }

    }

    when {
        openAlertDialog.value -> {
            TimePickerDialog(
                onDismiss = { openAlertDialog.value = false },
                onConfirm = { hour, minute ->
                    onEvent(AlarmListUiEvent.OnTimeSelected(hour, minute))
                    openAlertDialog.value = false
                }
            )
        }
    }

}

@Preview
@Composable
fun AlarmDetailScreenPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmDetailScreen(
            state = AlarmListState(
                isLoading = false,
                alarms = emptyList(),
                selectedAlarm = previewAlarm
            ),
            onEvent = { },
        )
    }
}