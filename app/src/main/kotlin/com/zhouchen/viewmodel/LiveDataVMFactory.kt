package com.zhouchen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhouchen.base.ui.IApp
import kotlinx.coroutines.Dispatchers

class LiveDataVMFactory(private val app : IApp) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiveDataViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
