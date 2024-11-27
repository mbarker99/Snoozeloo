package com.embarkapps.snoozeloo.alarms.data.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.embarkapps.snoozeloo.R
import com.embarkapps.snoozeloo.alarms.data.alarmscheduler.AlarmReceiver
import com.embarkapps.snoozeloo.alarms.domain.model.Alarm
import com.embarkapps.snoozeloo.alarms.presentation.TriggerActivity
import com.embarkapps.snoozeloo.core.domain.Constants
import java.time.ZoneId


@SuppressLint("MissingPermission")
fun Context.scheduleNotification(alarm: Alarm, isLockScreen: Boolean) {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    with(alarmManager) {
        setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarm.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            getReceiver(isLockScreen)
        )
    }
}

private fun Context.getReceiver(isLockScreen: Boolean): PendingIntent {
    // for demo purposes no request code and no flags
    return PendingIntent.getBroadcast(
        this,
        0,
        AlarmReceiver.build(this, isLockScreen),
        PendingIntent.FLAG_IMMUTABLE
    )
}

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
        notify(0, notification)
    }
}

private fun NotificationManager.buildChannel() {
    val name = "Alarms"
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance)

    createNotificationChannel(channel)
}

private fun Context.getFullScreenIntent(): PendingIntent {
    val intent = Intent(this, TriggerActivity::class.java)

    // flags and request code are 0 for the purpose of demonstration
    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
}
