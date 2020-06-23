/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhouchen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.zhouchen.application.di.component.DaggerLiveDataViewModelComponent
import com.zhouchen.application.di.component.LiveDataViewModelComponent
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.api.IAccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

/**
 * Showcases different patterns using the liveData coroutines builder.
 */
class LiveDataViewModel(app : IApp) : ViewModel() {

    @Inject
    lateinit var mAccess: IAccess

    private val injector: LiveDataViewModelComponent = DaggerLiveDataViewModelComponent
        .builder()
        .appSubcomponent(app.getAppSubcomponent())
        .build()

    init {
        injector.inject(this)
    }

    // Exposed LiveData from a function that returns a LiveData generated with a liveData builder
    val currentTime = mAccess.getCurrentTime()

    // Coroutines inside a transformation
    val currentTimeTransformed = currentTime.switchMap {
        // timeStampToTime is a suspend function so we need to call it from a coroutine.
        liveData { emit(timeStampToTime(it)) }
    }

    // Exposed liveData that emits and single value and subsequent values from another source.
    val currentWeather: LiveData<String> = liveData {
        emit(LOADING_STRING)
        emitSource(mAccess.fetchWeather())
    }

    // Exposed cached value in the data source that can be updated later on
    val cachedValue = mAccess.cachedData

    // Called when the user clicks on the "FETCH NEW DATA" button. Updates value in data source.
    fun onRefresh() {
        // Launch a coroutine that reads from a remote data source and updates cache
        viewModelScope.launch {
            mAccess.fetchNewData()
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
