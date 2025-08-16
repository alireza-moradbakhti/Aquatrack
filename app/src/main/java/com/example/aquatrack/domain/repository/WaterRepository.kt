package com.example.aquatrack.domain.repository

import com.example.aquatrack.data.dto.WaterInTakeRecord
import com.example.aquatrack.domain.model.WaterInTake
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a repository for managing water intake records.
 * Provides methods to add and retrieve water intake records.
 */
interface WaterRepository {

    /**
     * Adds a new water intake record to the repository.
     *
     * @param record The water intake record to be added.
     */
    suspend fun addWaterIntake(record: WaterInTakeRecord)

    /**
     * Retrieves all water intake records from the repository.
     *
     * @return A flow that emits a list of all water intake records.
     */
    fun getAllWaterIntakes(): Flow<List<WaterInTake>>

}