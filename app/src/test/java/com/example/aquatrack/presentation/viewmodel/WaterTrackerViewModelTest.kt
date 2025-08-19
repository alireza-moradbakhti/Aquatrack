package com.example.aquatrack.presentation.viewmodel

import app.cash.turbine.test
import com.example.aquatrack.core.util.MainCoroutineRule
import com.example.aquatrack.feature_home.domain.model.WaterInTake
import com.example.aquatrack.feature_home.domain.usecase.AddWaterInTakeUseCase
import com.example.aquatrack.feature_home.domain.usecase.DeleteWaterIntakeUseCase
import com.example.aquatrack.feature_home.domain.usecase.GetAllWaterInTakesUseCase
import com.example.aquatrack.feature_home.presentation.util.WaterTrackerEvent
import com.example.aquatrack.feature_home.presentation.viewmodel.WaterTrackerViewModel
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
    private lateinit var deleteWaterIntakeUseCase: DeleteWaterIntakeUseCase

    // The class we are testing
    private lateinit var viewModel: WaterTrackerViewModel

    @Before
    fun setUp() {
        // Initialize the mocks before each test
        addWaterIntakeUseCase = mockk(relaxed = true) // relaxed = true allows us to not mock every single function
        getAllWaterIntakesUseCase = mockk()
        deleteWaterIntakeUseCase = mockk(relaxed = true)

        // Before each test, we set up a default behavior for our mocks
        // Here, we make sure the "get all" use case returns an empty list initially
        coEvery { getAllWaterIntakesUseCase() } returns flowOf(emptyList())

        // Create a new instance of the ViewModel with the fake use cases
        viewModel = WaterTrackerViewModel(
            addWaterInTakeUseCase = addWaterIntakeUseCase,
            getAllWaterInTakesUseCase = getAllWaterIntakesUseCase,
            deleteWaterIntakeUseCase = deleteWaterIntakeUseCase
        )
    }

    @Test
    fun `when AddWaterClicked event is sent, uiState isAnimationPlaying should be true`() = runTest {
        viewModel.uiState.test {
            awaitItem() // Skip initial state

            viewModel.onEvent(WaterTrackerEvent.AddWaterClicked)

            val newState = awaitItem()
            assertTrue("isAnimationPlaying should be true after click", newState.isAnimationPlaying)

            coVerify { addWaterIntakeUseCase(250) }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when DeleteLastRecord event is sent, delete use case should be called`() = runTest {
        // ARRANGE: Create a fake record to delete
        val fakeRecord = WaterInTake(id = 1, timestamp = Date(), amountInMl = 250)

        // ACT: Send the delete event
        viewModel.onEvent(WaterTrackerEvent.DeleteLastRecord(fakeRecord))

        // ASSERT: Verify that the delete use case was called with the correct record
        coVerify { deleteWaterIntakeUseCase(fakeRecord) }
    }

    @Test
    fun `when viewmodel is initialized with empty list, glassesCount should be 0`() = runTest {
        // The default setup already returns an empty list.
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(0, initialState.glassesCount)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when viewmodel is initialized with a list of 3 records, glassesCount should be 3`() = runTest {
        // ARRANGE: Override mock to return a list with 3 items
        val fakeRecords = listOf(
            WaterInTake(1, Date(), 250),
            WaterInTake(2, Date(), 250),
            WaterInTake(3, Date(), 250)
        )
        coEvery { getAllWaterIntakesUseCase() } returns flowOf(fakeRecords)

        // ACT: Re-create the ViewModel to trigger the init block with new data
        viewModel = WaterTrackerViewModel(
            addWaterIntakeUseCase,
            deleteWaterIntakeUseCase,
            getAllWaterIntakesUseCase
        )

        // ASSERT: Check the glassesCount in the state
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(3, state.glassesCount)
            assertEquals(3, state.records.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

}