package com.zhouchen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhouchen.datalayer.api.IAccess

class LiveDataVMFactory(private val access: IAccess) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiveDataViewModel(access) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
