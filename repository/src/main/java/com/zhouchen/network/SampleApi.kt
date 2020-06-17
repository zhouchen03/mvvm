package com.zhouchen.network

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface SampleApi {
    /**
     * Get the list of the Article from the API
     */
    @GET("article/get_articles_list")
    fun getSamples(): Observable<SampleResponse>
}