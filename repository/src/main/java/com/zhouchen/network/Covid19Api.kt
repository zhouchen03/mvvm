package com.zhouchen.network

import androidx.annotation.WorkerThread
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface Covid19Api {
    /**
     * Get the list of the Article from the API
     */
    @WorkerThread
    @GET("covid19/v1/cases/newYorkTimes")
    suspend fun getCovid19Data(@Query("zipCode") zipCode: String,
                               @Query("daysInPast") daysInPast: Int = 7
    ): Response<CovidApiData>

}