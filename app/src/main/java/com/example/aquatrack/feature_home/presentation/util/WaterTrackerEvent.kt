package com.example.aquatrack.feature_home.presentation.util

import com.example.aquatrack.feature_home.domain.model.WaterInTake

// This sealed class defines the events that can occur in the water tracker feature.
sealed class WaterTrackerEvent {
    data object AddWaterClicked : WaterTrackerEvent()
    data object AnimationFinished : WaterTrackerEvent()
    data class DeleteLastRecord(val record: WaterInTake) : WaterTrackerEvent()
}