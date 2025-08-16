package com.example.aquatrack.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.aquatrack.notifications.AlarmScheduler
import com.example.aquatrack.notifications.WaterReminderReceiver

@HiltAndroidApp
class AquaTrackApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        AlarmScheduler.scheduleHourlyReminder(this)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                WaterReminderReceiver.NOTIFICATION_CHANNEL_ID,
                "Water Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders to drink water every hour"
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }




}