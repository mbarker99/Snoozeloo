package com.embarkapps.snoozeloo.alarms.presentation.alarmdetail

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmListScreen(
    state: AlarmDetailState,
    modifier: Modifier = Modifier
) {
    Card {

    }
}

@PreviewLightDark
@Composable
fun AlarmListScreenPreview(modifier: Modifier = Modifier) {
    SnoozelooTheme {
        AlarmListScreen(
            state = AlarmDetailState(
                isLoading = false,
                alarm = previewAlarm
            )
        )
    }
}