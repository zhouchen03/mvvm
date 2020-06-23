package com.zhouchen.viewmodel

import androidx.lifecycle.ViewModel
import com.zhouchen.base.ui.IApp
import com.zhouchen.di.component.DaggerSampleViewModelComponent
import com.zhouchen.di.component.SampleViewModelComponent


abstract class BaseViewModel(app: IApp): ViewModel(){
    private val injector: SampleViewModelComponent = DaggerSampleViewModelComponent
            .builder()
            .appSubcomponent(app.getAppSubcomponent())
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SampleListViewModel -> injector.inject(this)
            is SampleViewModel -> injector.inject(this)
        }
    }
}