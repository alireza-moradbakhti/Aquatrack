package com.example.aquatrack.feature_home.presentation.util

import com.example.aquatrack.feature_home.domain.model.WaterInTake

// This data class represents the UI state for the water tracker feature.
data class WaterTrackerUiState(
    val records: List<WaterInTake> = emptyList(),
    val isAnimationPlaying: Boolean = false,
    val dailyGoal: Int = 8,
    val glassesCount: Int = 0
)