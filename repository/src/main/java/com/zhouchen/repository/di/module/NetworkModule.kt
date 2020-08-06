package com.zhouchen.repository.di.module

import com.zhouchen.datalayer.api.IConfig
import com.zhouchen.network.SampleApi
import com.zhouchen.network.BuildConfig
import com.zhouchen.network.Covid19Api
import com.zhouchen.network.applyCallFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module which provides all required dependencies about network
 */

@Module
object NetworkModule {

    private const val NETWORK_CALL_TIMEOUT = 60

    /**
     * Provides the Sample service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Sample service implementation.
     */
    @Provides
    internal fun provideSampleApi(retrofit: Retrofit): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    internal fun provideRetrofitInterface(config : IConfig): Retrofit {
        return Retrofit.Builder()
                .baseUrl(config.getBaseURL())
                .client(
                    OkHttpClient.Builder()

                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                        })
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
                )
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    internal fun provideCovid19Api(): Covid19Api {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(this)
                }
            }
        }.build()
        return Retrofit.Builder()
            .baseUrl("https://localcoviddata.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .applyCallFactory { client.newCall(it) }
            .build()
            .create(Covid19Api::class.java)
    }
}

