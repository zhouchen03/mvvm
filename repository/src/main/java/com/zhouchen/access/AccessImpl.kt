package com.zhouchen.access

import android.content.Context
import androidx.room.Room
import com.zhouchen.database.SampleDao
import com.zhouchen.database.SampleDatabase
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
import javax.inject.Inject
import javax.inject.Provider


class AccessImpl : IAccess {
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

}