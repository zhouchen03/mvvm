package com.zhouchen.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.zhouchen.activity.R
import com.zhouchen.activity.SampleListAdapter
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.model.Sample
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SampleListViewModel(app : IApp): BaseViewModel(app){

    @Inject
    lateinit var mAccess: IAccess

    val sampleListAdapter: SampleListAdapter = SampleListAdapter(app)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadSamples() }

    private lateinit var subscription: Disposable

    init{
        loadSamples()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadSamples(){
        subscription = mAccess.getSamples()
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


    private fun onRetrieveSampleListSuccess(sampleList:List<Sample>){
        sampleListAdapter.updateSampleList(sampleList)
    }

    private fun onRetrieveSampleListError(){
        errorMessage.value = R.string.post_error
    }
}