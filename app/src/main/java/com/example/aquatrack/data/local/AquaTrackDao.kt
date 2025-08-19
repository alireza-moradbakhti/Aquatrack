package com.example.aquatrack.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aquatrack.data.dto.WaterInTakeRecord
import com.example.aquatrack.util.AppConstants
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the water_intake_records table.
 */
@Dao
interface AquaTrackDao {

    /**
     * Inserts a water intake record into the table. If the record already exists, it replaces it.
     * @param record The record to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: WaterInTakeRecord)

    /**
     * Retrieves all water intake records from the table, ordered by the most recent first.
     * @return A Flow emitting a list of all records.
     */
    @Query("SELECT * FROM ${AppConstants.WATER_INTAKE_TABLE} ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<WaterInTakeRecord>>

    /**
     * Deletes a specific water intake record from the table.
     * @param record The record to be deleted.
     */
    @Delete
    suspend fun delete(record: WaterInTakeRecord)
}