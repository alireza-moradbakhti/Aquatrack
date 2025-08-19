package com.example.aquatrack.util

import com.example.aquatrack.feature_home.data.dto.WaterInTakeRecord
import com.example.aquatrack.feature_home.domain.model.WaterInTake
import java.util.Date


/**
 * Extension function to map a WaterInTakeRecord DTO to a WaterInTake domain model.
 */
fun WaterInTakeRecord.toDomain(): WaterInTake {
    return WaterInTake(
        id = this.id,
        timestamp = Date(this.timestamp),
        amountInMl = this.amountInMl
    )
}

/**
 * Extension function to map a list of WaterInTakeRecord DTOs to a list of WaterInTake domain models.
 */
fun List<WaterInTakeRecord>.toDomain(): List<WaterInTake> {
    return this.map { it.toDomain() }
}

/**
 * Extension function to map a WaterInTake domain model to a WaterInTakeRecord DTO.
 */
fun WaterInTake.toDto(): WaterInTakeRecord {
    return WaterInTakeRecord(
        id = this.id,
        timestamp = this.timestamp.time,
        amountInMl = this.amountInMl
    )
}