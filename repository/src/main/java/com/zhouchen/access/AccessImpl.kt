package com.zhouchen.access

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.zhouchen.database.SampleDao
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.model.Sample
import com.zhouchen.network.SampleApi
import com.zhouchen.repository.di.module.DatabaseModule
import com.zhouchen.repository.di.module.NetworkModule
import com.zhouchen.repository.di.subcomponent.AccessSubcomponent
import io.reactivex.Observable
import io.reactivex.Observable.fromCallable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider


open class AccessImpl : IAccess {
    @Inject
    lateinit var mSampleDao: SampleDao
    @Inject
    lateinit var mSampleApi: SampleApi

    constructor(provider: Provider<AccessSubcomponent.Builder>) {
        provider.get()
            .databaseModule(DatabaseModule)
            .networkModule(NetworkModule)
            .build().inject(this)
    }

    override fun getSamples(): Observable<List<Sample>> {
        return fromCallable { mSampleDao.all }
            .concatMap {
                    dbSampleList ->
                if(dbSampleList.isEmpty()) {
                    mSampleApi.getSamples().concatMap { apiSampleResponse ->
                        mSampleDao.nukeTable()
                        mSampleDao.insertAll(apiSampleResponse.content)
                        Observable.just(apiSampleResponse.content)
                    }
                }
                else {
                    Observable.just(dbSampleList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Observable<List<Sample>>
    }

    //showcasing different LiveData + coroutines patterns.
    /**
     * LiveData builder generating a value that will be transformed.
     */
    override fun getCurrentTime(): LiveData<Long> =
        liveData {
            while (true) {
                emit(System.currentTimeMillis())
                delay(1000)
            }
        }

    /**
     * emit + emitSource pattern (see ViewModel).
     */

    // Exposes a LiveData of changing weather conditions, every 2 seconds.
    private val weatherConditions = listOf("Sunny", "Cloudy", "Rainy", "Stormy", "Snowy")

    override fun fetchWeather(): LiveData<String> = liveData {
        var counter = 0
        while (true) {
            counter++
            delay(2000)

            emit(weatherConditions[counter % weatherConditions.size])
        }
    }

    /**
     * Cache + Remote data source.
     */

    // Cache of a data point that is exposed to VM
    private val _cachedData = MutableLiveData("This is old data")
    override val cachedData: LiveData<String> = _cachedData

    // Called when the cache needs to be refreshed. Must be called from coroutine.
    override suspend fun fetchNewData() {
        // Force Main thread
        withContext(Dispatchers.Main) {
            _cachedData.value = "Fetching new data..."
            _cachedData.value = simulateNetworkDataFetch()
        }
    }

    // Fetches new data in the background. Must be called from coroutine so it's scoped correctly.
    private var counter = 0
    // Using ioDispatcher because the function simulates a long and expensive operation.
    private suspend fun simulateNetworkDataFetch(): String = withContext(Dispatchers.IO) {
        delay(3000)
        counter++
        "New data from request #$counter"
    }

}