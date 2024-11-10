package com.embarkapps.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListScreen
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.AlarmListState
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                AlarmListScreen(
                    state = AlarmListState(
                        isLoading = false,
                        alarms = (1..5).map {
                            previewAlarm.copy(
                                id = it
                            )
                        }
                    )
                )
            }
        }
    }
}