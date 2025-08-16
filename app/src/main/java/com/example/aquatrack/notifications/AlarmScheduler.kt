package com.example.aquatrack.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

/**
 * AlarmScheduler is a utility class that manages the scheduling of alarms for water reminders.
 * It sets up an alarm that triggers a notification to remind users to drink water at specified intervals.
 */
object AlarmScheduler {
    /**
     * Schedules an hourly reminder for water intake.
     *
     * @param context The context in which the alarm is being set.
     */
    fun scheduleHourlyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WaterReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set the alarm to start at the beginning of the next hour
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            add(Calendar.HOUR_OF_DAY, 1)
        }

        // Use setInexactRepeating for better battery performance
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
    }

}