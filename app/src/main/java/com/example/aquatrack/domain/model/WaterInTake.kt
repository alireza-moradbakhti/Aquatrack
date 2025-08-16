package com.example.aquatrack.domain.model

import java.util.Date

data class WaterInTake(
    val id: Int,
    val timestamp: Date,
    val amountInMl: Int
)
