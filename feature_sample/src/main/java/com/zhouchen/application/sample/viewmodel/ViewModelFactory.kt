package com.zhouchen.application.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhouchen.application.sample.activity.SampleListAdapter
import com.zhouchen.datalayer.api.IAccess

class ViewModelFactory(private val access : IAccess): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SampleListViewModel(access, SampleListAdapter()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}