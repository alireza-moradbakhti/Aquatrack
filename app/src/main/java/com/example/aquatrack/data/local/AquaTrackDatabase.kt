package com.example.aquatrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aquatrack.data.dto.WaterInTakeRecord
import com.example.aquatrack.util.AppConstants

/**
 * The Room database for this app.
 */
@Database(entities = [WaterInTakeRecord::class], version = 1 , exportSchema = false)
abstract class AquaTrackDatabase : RoomDatabase() {

    /**
     * Abstract method to get the DAO for water intake records.
     * This method will be implemented by Room to provide the DAO instance.
     */
    abstract fun aquaTrackDao(): AquaTrackDao

    /**
     * Companion object to hold constants related to the database.
     * This allows easy access to the database name without needing an instance of the class.
     */
    companion object {
        const val DATABASE_NAME = AppConstants.DATABASE_NAME
    }
}