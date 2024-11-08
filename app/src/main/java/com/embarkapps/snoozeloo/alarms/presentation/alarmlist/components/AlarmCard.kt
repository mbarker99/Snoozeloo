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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.ui.theme.SnoozelooTheme
import java.time.ZonedDateTime

@Composable
fun AlarmCard(
    alarm: Alarm,
    isExtended: Boolean,
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
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Title and Switch
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = alarm.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 19.5.sp,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = alarm.isEnabled,
                    onCheckedChange = {},
                    colors = switchColors
                )
            }

            // Time and AM/PM
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "10:00",
                    fontSize = 42.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 51.2.sp,
                )
                Text(
                    text = "AM",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 29.26.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

            }
            Text(
                text = "Alarm in 5h 30min",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 17.07.sp,
                color = Color(0xFF858585)
            )
        }
    }

}

@PreviewLightDark
@Composable
private fun AlarmCardPreview() {
    SnoozelooTheme {
        AlarmCard(
            alarm = previewAlarm
        )
    }

}

internal val previewAlarm = Alarm(
    id = 1,
    title = "Wake up",
    isEnabled = true,
    time = ZonedDateTime.now()
)
