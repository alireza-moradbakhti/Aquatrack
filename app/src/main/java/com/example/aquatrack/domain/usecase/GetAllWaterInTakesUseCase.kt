package com.example.aquatrack.domain.usecase

import com.example.aquatrack.domain.model.WaterInTake
import com.example.aquatrack.domain.repository.WaterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllWaterInTakesUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {

    /**
     * Retrieves all water intake records from the repository.
     *
     * @return A flow that emits a list of all water intake records.
     */
    operator fun invoke(): Flow<List<WaterInTake>> {
        return waterRepository.getAllWaterIntakes()
    }
}