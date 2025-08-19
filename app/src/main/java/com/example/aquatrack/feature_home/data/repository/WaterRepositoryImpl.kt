package com.example.aquatrack.feature_home.data.repository

import com.example.aquatrack.feature_home.data.dto.WaterInTakeRecord
import com.example.aquatrack.feature_home.data.local.AquaTrackDao
import com.example.aquatrack.feature_home.domain.model.WaterInTake
import com.example.aquatrack.feature_home.domain.repository.WaterRepository
import com.example.aquatrack.util.toDomain
import com.example.aquatrack.util.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the WaterRepository interface for managing water intake records.
 * This class interacts with the AquaTrackDao to perform database operations.
 *
 * @property waterDao The DAO for accessing water intake records in the database.
 */
class WaterRepositoryImpl @Inject constructor(
    private val waterDao: AquaTrackDao
) : WaterRepository {
    /**
     * Adds a new water intake record to the repository.
     *
     * @param record The water intake record to be added.
     */
    override suspend fun addWaterIntake(record: WaterInTakeRecord) {
        waterDao.insert(record)
    }

    /**
     * Retrieves all water intake records from the repository.
     *
     * @return A flow that emits a list of all water intake records.
     */
    override fun getAllWaterIntakes(): Flow<List<WaterInTake>> {
        return waterDao.getAllRecords().map { it.toDomain() }
    }


    /**
     * Deletes a specific water intake record from the repository.
     *
     * @param record The water intake record to be deleted.
     */
    override suspend fun deleteWaterIntake(record: WaterInTake) {
        waterDao.delete(record.toDto())
    }


}