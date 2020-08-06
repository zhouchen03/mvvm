package com.zhouchen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhouchen.datalayer.api.IAccess

class Covid19LiveDataVMFactory(private val access: IAccess) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Covid19DataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Covid19DataViewModel(access) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
