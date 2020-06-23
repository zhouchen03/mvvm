package com.zhouchen.datalayer.api

import androidx.lifecycle.LiveData
import com.zhouchen.datalayer.model.Sample
import io.reactivex.Observable

interface IAccess {
    fun getSamples(): Observable<List<Sample>>
    fun getCurrentTime(): LiveData<Long>
    fun fetchWeather(): LiveData<String>
    val cachedData: LiveData<String>
    suspend fun fetchNewData()
}