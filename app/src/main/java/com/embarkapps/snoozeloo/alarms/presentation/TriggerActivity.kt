package com.embarkapps.snoozeloo.alarms.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.presentation.alarmtrigger.AlarmTriggerScreen
import com.embarkapps.snoozeloo.alarms.presentation.model.toAlarmUi
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class TriggerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {

                val alarm = Alarm(
                    title = intent.getStringExtra("EXTRA_TITLE") ?: "",
                    time = LocalDateTime.now(),
                    isEnabled = true,
                    id = 0
                )
                AlarmTriggerScreen(
                    alarm = alarm.toAlarmUi(),
                    onTurnOff = { finishActivity(0) },
                )
            }
        }
    }
}