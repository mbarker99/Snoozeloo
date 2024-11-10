package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.montserratFontFamily

@Composable
fun AlarmDetailScreen(
    state: AlarmDetailState,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val closeButtonColors = IconButtonDefaults.iconButtonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = Color(0xFFE6E6E6),
        disabledContentColor = Color.White
    )

    val saveButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = Color(0xFFE6E6E6),
        disabledContentColor = Color.White
    )

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
                onClick = { onCloseClick() },
                enabled = true,
                colors = saveButtonColors,
                shape = RoundedCornerShape(10.dp),
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

            Button(
                onClick = { },
                enabled = state.isValid,
                colors = saveButtonColors,
                content = {
                    Text(
                        text = "Save",
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 19.5.sp,
                        textAlign = TextAlign.Center
                    )
                },
                modifier = Modifier
                    .height(32.dp)
            )

        }

        TimeCard(
            hour = state.alarm.time.hour,
            minute = state.alarm.time.minute,
        )

        AlarmNameCard(
            title = state.alarm.title,
        )

        if (state.isExtended) {
            // TODO : add additional logic here
        }

    }

}

@Preview
@Composable
fun AlarmDetailScreenPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmDetailScreen(
            state = AlarmDetailState(
                isLoading = false,
                alarm = previewAlarm,
                isValid = false
            ),
            onCloseClick = {}
        )
    }
}