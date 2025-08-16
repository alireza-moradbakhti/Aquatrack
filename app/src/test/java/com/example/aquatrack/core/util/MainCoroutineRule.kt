package com.example.aquatrack.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description


// This file sets up a rule for testing coroutines in Android, allowing tests to run on a controlled dispatcher.
// It uses the UnconfinedTestDispatcher to run tests in a coroutine context that is not
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    /**
     * This rule sets the main dispatcher to a test dispatcher before each test and resets it after each test.
     * This allows for controlled execution of coroutines in tests, avoiding the need for real-time delays.
     */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * This method is called after each test, resetting the main dispatcher to its original state.
     * This ensures that the test environment is clean for the next test.
     */
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }


}