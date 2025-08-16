package com.example.aquatrack.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.aquatrack.R
import com.example.aquatrack.util.AppConstants

/**
 * WaterReminderReceiver is a BroadcastReceiver that handles water reminder notifications.
 * It is triggered by an alarm set in the app to remind users to drink water.
 */
class WaterReminderReceiver : BroadcastReceiver() {
    /**
     * This method is called when the BroadcastReceiver receives an Intent broadcast.
     * It is used to handle the water reminder notification logic.
     *
     * @param context The context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel if it doesn't exist (required for Android O and above)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notify) // Replace with a proper water drop icon
            .setContentTitle("Time for Water! 💧")
            .setContentText("Stay hydrated. It's time to drink a glass of water.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    /**
     * Companion object to hold constants related to the notification.
     */
    companion object {
        const val NOTIFICATION_ID = AppConstants.NOTIFICATION_ID
        const val NOTIFICATION_CHANNEL_ID = AppConstants.NOTIFICATION_CHANNEL_ID
    }
}