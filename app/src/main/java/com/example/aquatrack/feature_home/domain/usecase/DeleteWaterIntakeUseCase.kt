package com.example.aquatrack.feature_home.domain.usecase

import com.example.aquatrack.feature_home.domain.model.WaterInTake
import com.example.aquatrack.feature_home.domain.repository.WaterRepository
import javax.inject.Inject

/**
 * Use case for deleting a water intake record.
 *
 * @property repository The repository to interact with water intake data.
 */
class DeleteWaterIntakeUseCase @Inject constructor(
    private val repository: WaterRepository
) {

    /**
     * Deletes a water intake record.
     *
     * @param record The water intake record to delete.
     */
    suspend operator fun invoke(record: WaterInTake) {
        repository.deleteWaterIntake(record)
    }
}