package com.example.aquatrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquatrack.domain.usecase.AddWaterInTakeUseCase
import com.example.aquatrack.domain.usecase.GetAllWaterInTakesUseCase
import com.example.aquatrack.presentation.util.WaterTrackerEvent
import com.example.aquatrack.presentation.util.WaterTrackerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaterTrackerViewModel @Inject constructor(
    private val addWaterInTakeUseCase: AddWaterInTakeUseCase,
    getAllWaterInTakesUseCase: GetAllWaterInTakesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WaterTrackerUiState())
    val uiState: StateFlow<WaterTrackerUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllWaterInTakesUseCase().collect { records ->
                _uiState.update {
                    it.copy(
                        records = records,
                        glassesCount = records.size
                    )
                }
            }
        }
    }

    /**
     * Central point for handling all UI events.
     */
    fun onEvent(event: WaterTrackerEvent) {
        when (event) {
            is WaterTrackerEvent.AddWaterClicked -> {
                viewModelScope.launch {
                    addWaterInTakeUseCase(amount = 250)
                    _uiState.update { it.copy(isAnimationPlaying = true) }
                }
            }
            is WaterTrackerEvent.AnimationFinished -> {
                _uiState.update { it.copy(isAnimationPlaying = false) }
            }

        }
    }
}