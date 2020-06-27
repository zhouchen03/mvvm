package com.zhouchen.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.model.Sample
import io.reactivex.Observable
import io.reactivex.Observable.fromCallable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList


/**
 * Unit tests for [LiveDataViewModel].
 */
@ExperimentalCoroutinesApi
class LiveDataViewModelTest {

    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Use a Fake DataSource so we have all necessary control over it
    private val fakeDataSource = FakeDataSource()

    // Class under test. Uses Dispatchers.Main so that the MainCoroutineRule can control it.
    private lateinit var viewModel: LiveDataViewModel

    @Before
    fun init() {
        // Initialize the ViewModel after the [MainCoroutineRule] is applied so that it has the
        // right test dispatcher.
        viewModel = LiveDataViewModel(fakeDataSource)
    }

    @Test
    fun getCurrentTime_notEmpty() {
        val cachedValue = viewModel.cachedValue.getOrAwaitValue()
        assertTrue(cachedValue.isNotEmpty())
    }

    @Test
    fun currentTimeTransformed() {
        // Get the result of a coroutine inside a transformation
        val timeTransformed = viewModel.currentTimeTransformed.getOrAwaitValue {
            // After observing, advance the clock to avoid the delay calls.
            mainCoroutineRule.advanceUntilIdle()
        }
        assertEquals(timeTransformed, Date(FakeDataSource.CURRENT_TIME).toString())
    }

    @Test
    fun getCurrentWeather_loading() {
        // Start with a paused dispatcher in the FakeDataSource
        fakeDataSource.testDispatcher.pauseDispatcher()

        // Keep observing currentWeather
        viewModel.currentWeather.observeForTesting {

            // Verify that the first value is Loading
            assertEquals(viewModel.currentWeather.value, LiveDataViewModel.LOADING_STRING)

            // Resume fake dispatcher so it emits a new value
            fakeDataSource.testDispatcher.resumeDispatcher()

            // Verify the new value is available
            assertEquals(viewModel.currentWeather.value, FakeDataSource.WEATHER_CONDITION)
        }
    }

    @Test
    fun cache_RefreshFromViewModelScope() {
        // Get the initial value that comes directly from FakeDataSource
        val initialValue = viewModel.cachedValue.getOrAwaitValue()

        // Trigger an update, which starts a coroutine that updates the value
        viewModel.onRefresh()

        // Get the new value
        val valueAfterRefresh = viewModel.cachedValue.getOrAwaitValue()

        // Assert they are different values
        assertNotEquals(initialValue, valueAfterRefresh)
        assertEquals(initialValue, FakeDataSource.CURRENT_VALUE)
        assertEquals(valueAfterRefresh, FakeDataSource.NEW_VALUE)
    }
}

@ExperimentalCoroutinesApi
class FakeDataSource : IAccess {

    companion object {
        const val CURRENT_VALUE = "test"
        const val CURRENT_TIME = 123456781234
        const val WEATHER_CONDITION = "Sunny test"
        const val NEW_VALUE = "new value"
    }

    private val _currentValue = MutableLiveData<String>(CURRENT_VALUE)
    override val cachedData: LiveData<String> = _currentValue
    override fun getSamples(): Observable<List<Sample>> {
        return fromCallable { ArrayList<Sample>() }
    }

    override fun getCurrentTime(): LiveData<Long> = MutableLiveData<Long>(CURRENT_TIME)

    val testDispatcher = TestCoroutineDispatcher()
    override fun fetchWeather(): LiveData<String> = liveData(testDispatcher) {
        emit(WEATHER_CONDITION)
    }

    override suspend fun fetchNewData() {
        _currentValue.value = NEW_VALUE
    }
}