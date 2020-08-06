package com.zhouchen.datalayer.api

import androidx.lifecycle.LiveData
import com.zhouchen.datalayer.model.*
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface IAccess {
    // RxJava
    fun getSamples(): Observable<List<Sample>>

    // Coroutines
    fun getCurrentTime(): LiveData<Long>
    fun fetchWeather(): LiveData<String>
    val cachedData: LiveData<String>
    suspend fun fetchNewData()

    // Coroutines
    fun getCovid19Data(zip: String, daysInPast: Int): Flow<Result<DbCovid19Data>>
}