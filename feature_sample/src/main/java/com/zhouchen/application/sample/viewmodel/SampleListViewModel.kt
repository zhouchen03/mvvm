package com.zhouchen.application.sample.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhouchen.application.sample.R
import com.zhouchen.application.sample.activity.SampleListAdapter
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.model.Sample
import io.reactivex.disposables.Disposable

class SampleListViewModel(private val access : IAccess, private val adapter: SampleListAdapter): ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadSamples() }

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
    fun getSampleListAdapter(): SampleListAdapter {
        return adapter
    }

    fun loadSamples(){
        subscription = access.getSamples()
            .doOnSubscribe { onRetrieveSampleListStart() }
            .doOnTerminate { onRetrieveSampleListFinish() }
            .subscribe(
                { result -> onRetrieveSampleListSuccess(result as List<Sample>) },
                { onRetrieveSampleListError() }
            )
    }

    private fun onRetrieveSampleListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveSampleListFinish(){
        loadingVisibility.value = View.GONE
    }


    fun onRetrieveSampleListSuccess(sampleList:List<Sample>) {
        adapter.updateSampleList(sampleList)
    }

    fun onRetrieveSampleListError(){
        errorMessage.value = R.string.post_error
    }
}