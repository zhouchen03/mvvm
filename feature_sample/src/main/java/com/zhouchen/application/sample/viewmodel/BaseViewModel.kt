package com.zhouchen.application.sample.viewmodel

import androidx.lifecycle.ViewModel
import com.zhouchen.application.sample.di.component.DaggerSampleViewModelComponent
import com.zhouchen.base.ui.IApp
import com.zhouchen.application.sample.di.component.SampleViewModelComponent


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