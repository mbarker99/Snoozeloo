package com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.alarms.data.model.AlarmEntity
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListUiEvent
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AlarmCard(
    alarm: Alarm,
    isExtended: Boolean,
    onEvent: (AlarmListUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val cardColors = CardDefaults.cardColors().copy(
        containerColor = Color.White,
        contentColor = Color.Black,
        disabledContainerColor = Color.White,
        disabledContentColor = Color.Black
    )

    val switchColors = SwitchDefaults.colors().copy(
        checkedThumbColor = Color.White,
        checkedTrackColor = Color(0xFF4664FF)
    )

    Card(
        colors = cardColors,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = alarm.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 19.5.sp,
                    fontFamily = montserratFontFamily,
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // TODO : implement time calculation here
                    val formatter = DateTimeFormatter.ofPattern("HH:MM")
                    val formattedTime = alarm.time.format(formatter)
                    Text(
                        text = formattedTime,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 51.2.sp,
                        fontFamily = montserratFontFamily,
                    )
                    val zoneFormatter = DateTimeFormatter.ofPattern("a")
                    val formattedAMPM = alarm.time.format(zoneFormatter)
                    Text(
                        text = formattedAMPM,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 29.26.sp,
                        fontFamily = montserratFontFamily,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                // TODO : calculate remaining time here
                Text(
                    text = "Alarm in",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 17.07.sp,
                    fontFamily = montserratFontFamily,
                    color = Color(0xFF858585)
                )


            }
            Switch(
                checked = alarm.isEnabled,
                onCheckedChange = { onEvent(AlarmListUiEvent.OnAlarmEnableToggle(alarm)) },
                colors = switchColors
            )
        }
    }

}

@Preview
@Composable
private fun AlarmCardPreview() {
    SnoozelooTheme {
        AlarmCard(
            alarm = previewAlarm,
            isExtended = false,
            onEvent = {}
        )
    }

}

internal val previewAlarm = AlarmEntity(
    id = 1,
    title = "Wake up",
    isEnabled = true,
    hour = LocalDateTime.now().hour,
    minute = LocalDateTime.now().minute
).toAlarm()
