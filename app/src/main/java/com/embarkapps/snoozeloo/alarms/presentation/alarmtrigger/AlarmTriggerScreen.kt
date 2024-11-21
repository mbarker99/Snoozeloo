package com.embarkapps.snoozeloo.alarms.presentation.alarmtrigger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.snoozeloo.R
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.model.AlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.BluePrimary
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@Composable
fun AlarmTriggerScreen(
    alarm: AlarmUi,
    onTurnOff: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_alarm),
            contentDescription = "Alarm",
            modifier = Modifier.size(56.dp),
            tint = BluePrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "${alarm.hour.formattedValue}:${alarm.minute.formattedValue}",
            color = BluePrimary,
            fontSize = 82.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = montserratFontFamily,
            lineHeight = 100.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = alarm.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = montserratFontFamily,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = onTurnOff,
            content = {
                Text(
                    text = "Turn Off",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = montserratFontFamily,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(vertical = 8.dp)
                )
            }
        )

    }
}

@Preview
@Composable
fun AlarmTriggerScreenPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmTriggerScreen(
            alarm = previewAlarm,
            onTurnOff = {},
        )
    }
}