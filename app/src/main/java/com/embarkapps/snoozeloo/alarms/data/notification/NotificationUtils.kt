package com.embarkapps.snoozeloo.alarms.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.embarkapps.snoozeloo.R
import com.embarkapps.snoozeloo.alarms.presentation.TriggerActivity
import com.embarkapps.snoozeloo.core.domain.Constants

fun Context.showNotificationWithFullScreenIntent(
    isLockScreen: Boolean = false,
    channelId: String = Constants.CHANNEL_ID,
    title: String = "Title",

    ) {
    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_alarm)
        .setContentTitle(title)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setFullScreenIntent(getFullScreenIntent(), true)


    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildChannel()

        val notification = builder.build()
        Log.d("NotificationUtils", "Notification built.")
        notify(0, notification)
    }
}

private fun NotificationManager.buildChannel() {
    val name = "Alarms"
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance)

    createNotificationChannel(channel)
    Log.d("NotificationUtils", "Notification channel built.")
}

private fun Context.getFullScreenIntent(): PendingIntent {
    val intent = Intent(this, TriggerActivity::class.java)

    // flags and request code are 0 for the purpose of demonstration
    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
}
