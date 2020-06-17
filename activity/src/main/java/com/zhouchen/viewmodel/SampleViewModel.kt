package com.zhouchen.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhouchen.base.ui.IApp
import com.zhouchen.base.utils.getFormatedDate
import com.zhouchen.datalayer.model.Sample

class SampleViewModel(app : IApp): BaseViewModel(app){
    private val title = MutableLiveData<String>()
    private val ingress = MutableLiveData<String>()
    private  val image = MutableLiveData<String>()
    private  val date = MutableLiveData<String>()

    fun bind(sample: Sample){
        title.value = sample.title
        ingress.value = sample.ingress
        image.value = sample.image
        date.value = sample.dateTime
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getIngress():MutableLiveData<String>{
        return ingress
    }

    fun getImage():MutableLiveData<String>{
        return image
    }

    fun getDate():MutableLiveData<String>{
        val modifiedDate = getFormatedDate(date.value.toString())
        date.value = modifiedDate
        return date
    }
}