package com.embarkapps.snoozeloo.alarms.data.alarmscheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.embarkapps.snoozeloo.alarms.data.notification.showNotificationWithFullScreenIntent
import com.embarkapps.snoozeloo.core.domain.Constants.LOCK_SCREEN_KEY

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val title = intent?.getStringExtra("EXTRA_TITLE") ?: return
        Log.d("AlarmReceiver", "Alarm triggered: $title")
        context?.showNotificationWithFullScreenIntent(
            title = title
        )
    }

    companion object {
        fun build(context: Context, isLockScreen: Boolean): Intent {
            return Intent(context, AlarmReceiver::class.java).also {
                it.putExtra(LOCK_SCREEN_KEY, isLockScreen)
            }
        }
    }
}