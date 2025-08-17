package com.example.aquatrack.presentation.viewmodel

import app.cash.turbine.test
import com.example.aquatrack.core.util.MainCoroutineRule
import com.example.aquatrack.domain.model.WaterInTake
import com.example.aquatrack.domain.usecase.AddWaterInTakeUseCase
import com.example.aquatrack.domain.usecase.GetAllWaterInTakesUseCase
import com.example.aquatrack.presentation.util.WaterTrackerEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class WaterTrackerViewModelTest {

    // This rule swaps the main dispatcher with a test dispatcher
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // We create "mock" (fake) versions of our dependencies
    private lateinit var addWaterIntakeUseCase: AddWaterInTakeUseCase
    private lateinit var getAllWaterIntakesUseCase: GetAllWaterInTakesUseCase

    // The class we are testing
    private lateinit var viewModel: WaterTrackerViewModel

    @Before
    fun setUp() {
        // Initialize the mocks before each test
        addWaterIntakeUseCase = mockk(relaxed = true) // relaxed = true allows us to not mock every single function
        getAllWaterIntakesUseCase = mockk()

        // Before each test, we set up a default behavior for our mocks
        // Here, we make sure the "get all" use case returns an empty list initially
        coEvery { getAllWaterIntakesUseCase() } returns flowOf(emptyList())

        // Create a new instance of the ViewModel with the fake use cases
        viewModel = WaterTrackerViewModel(
            addWaterInTakeUseCase = addWaterIntakeUseCase,
            getAllWaterInTakesUseCase = getAllWaterIntakesUseCase
        )
    }

    @Test
    fun `when AddWaterClicked event is sent, uiState isAnimationPlaying should be true`() = runTest {
        // Use the 'test' function from Turbine to test the StateFlow
        viewModel.uiState.test {
            // 1. Skip the initial state emission
            awaitItem()

            // 2. Send the event to the ViewModel
            viewModel.onEvent(WaterTrackerEvent.AddWaterClicked)

            // 3. Await the new state emission and assert its value
            val newState = awaitItem()
            assertTrue("isAnimationPlaying should be true after click", newState.isAnimationPlaying)

            // 4. Verify that our "add" use case was actually called
            coVerify { addWaterIntakeUseCase(250) }

            // 5. Cancel the collector to avoid leaks
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when viewmodel is initialized, it should show first time hint if records are empty`() = runTest {
        // The setup already configures the mock to return an empty list.
        // We just need to check the initial state.
        val initialState = viewModel.uiState.value
        assertTrue("showFirstTimeHint should be true for empty list", initialState.showFirstTimeHint)
    }

    @Test
    fun `when viewmodel is initialized, it should not show first time hint if records exist`() = runTest {
        // ARRANGE: Override the default mock behavior for this specific test
        val fakeRecords = listOf(WaterInTake(1, Date(), 250))
        coEvery { getAllWaterIntakesUseCase() } returns flowOf(fakeRecords)

        // ACT: Re-create the ViewModel to trigger the init block with the new mock data
        viewModel = WaterTrackerViewModel(addWaterIntakeUseCase, getAllWaterIntakesUseCase)

        // ASSERT: Check the state
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(false, state.showFirstTimeHint)
            assertEquals(1, state.records.size)
            cancelAndIgnoreRemainingEvents()
        }
    }
}