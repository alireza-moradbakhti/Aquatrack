package com.example.aquatrack.feature_home.domain.usecase

import com.example.aquatrack.feature_home.data.dto.WaterInTakeRecord
import com.example.aquatrack.feature_home.domain.repository.WaterRepository
import javax.inject.Inject

/**
 * Use case for adding a new water intake record.
 * This class encapsulates the logic for adding a water intake record to the repository.
 *
 * @property waterRepository The repository for managing water intake records.
 */
class AddWaterInTakeUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {


    suspend operator fun invoke(amount: Int) {
        // The UseCase is now responsible for creating the DTO
        val recordDto = WaterInTakeRecord(
            timestamp = System.currentTimeMillis(),
            amountInMl = amount
        )
        waterRepository.addWaterIntake(recordDto)
    }
}