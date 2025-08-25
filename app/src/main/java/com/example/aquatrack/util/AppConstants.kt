package com.example.aquatrack.util

/**
 * This object holds all the constants used throughout the AquaTrack application.
 * It includes database names, table names, notification IDs, and navigation routes and etc.
 * These constants help maintain consistency and avoid hardcoding values in multiple places.
 * They can be easily modified in one place if needed.
 */
object AppConstants {

    // Database and Shared Preferences
    const val DATABASE_NAME = "aqua_track_db"
    const val WATER_INTAKE_TABLE = "water_intake_records"

    // Notification Constants
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "water_reminder_channel"

    // Navigation Routes
    const val WELCOME_ROUTE = "welcome_screen"
    const val LOGIN_ROUTE = "login_screen"
    const val SIGNUP_ROUTE = "signup_screen"
    const val HOME_ROUTE = "home_screen"
    const val AWARDS_ROUTE = "awards_screen"
    const val PROFILE_ROUTE = "profile_screen"
    const val FORGOT_PASSWORD_ROUTE = "forgot_password_screen"


}