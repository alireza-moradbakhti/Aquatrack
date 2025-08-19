package com.example.aquatrack.feature_home.domain.model

import java.util.Date

data class WaterInTake(
    val id: Int,
    val timestamp: Date,
    val amountInMl: Int
)
