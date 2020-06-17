package com.zhouchen.datalayer.api

import com.zhouchen.datalayer.model.Sample
import io.reactivex.Observable

interface IAccess {
    fun getSamples(): Observable<List<Sample>>
}