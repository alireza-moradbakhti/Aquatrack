package com.example.aquatrack.presentation.util

import com.example.aquatrack.domain.model.WaterInTake

// This data class represents the UI state for the water tracker feature.
data class WaterTrackerUiState(
    val records: List<WaterInTake> = emptyList(),
    val isAnimationPlaying: Boolean = false,
    val showFirstTimeHint: Boolean = false
)