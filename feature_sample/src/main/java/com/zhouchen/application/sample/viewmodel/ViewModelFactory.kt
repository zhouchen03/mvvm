package com.zhouchen.application.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhouchen.base.ui.IApp

class ViewModelFactory(private val app : IApp): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SampleListViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}