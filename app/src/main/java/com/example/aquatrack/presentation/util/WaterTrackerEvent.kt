package com.example.aquatrack.presentation.util

// This sealed class defines the events that can occur in the water tracker feature.
sealed class WaterTrackerEvent {
    data object AddWaterClicked : WaterTrackerEvent()
    data object AnimationFinished : WaterTrackerEvent()
}