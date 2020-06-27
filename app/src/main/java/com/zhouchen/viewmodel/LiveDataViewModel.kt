package com.zhouchen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.zhouchen.datalayer.api.IAccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

/**
 * Showcases different patterns using the liveData coroutines builder.
 */
class LiveDataViewModel(private val access: IAccess) : ViewModel() {

    // Exposed LiveData from a function that returns a LiveData generated with a liveData builder
    val currentTime = access.getCurrentTime()

    // Coroutines inside a transformation
    val currentTimeTransformed = currentTime.switchMap {
        // timeStampToTime is a suspend function so we need to call it from a coroutine.
        liveData { emit(timeStampToTime(it)) }
    }

    // Exposed liveData that emits and single value and subsequent values from another source.
    val currentWeather: LiveData<String> = liveData {
        emit(LOADING_STRING)
        emitSource(access.fetchWeather())
    }

    // Exposed cached value in the data source that can be updated later on
    val cachedValue = access.cachedData

    // Called when the user clicks on the "FETCH NEW DATA" button. Updates value in data source.
    fun onRefresh() {
        // Launch a coroutine that reads from a remote data source and updates cache
        viewModelScope.launch {
            access.fetchNewData()
        }
    }

    // Simulates a long-running computation in a background thread
    private suspend fun timeStampToTime(timestamp: Long): String {
        delay(500)  // Simulate long operation
        val date = Date(timestamp)
        return date.toString()
    }

    companion object {
        // Real apps would use a wrapper on the result type to handle this.
        const val LOADING_STRING = "Loading..."
    }
}
