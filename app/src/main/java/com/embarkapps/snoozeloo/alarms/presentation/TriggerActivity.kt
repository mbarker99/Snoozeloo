package com.embarkapps.snoozeloo.alarms.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.embarkapps.snoozeloo.alarms.presentation.alarmlist.components.previewAlarm
import com.embarkapps.snoozeloo.alarms.presentation.alarmtrigger.AlarmTriggerScreen
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriggerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                Log.d("AlarmReceiver", "the TriggerActivity has been launched")
                AlarmTriggerScreen(
                    alarm = previewAlarm,
                    onTurnOff = { },
                )
            }
        }
    }
}