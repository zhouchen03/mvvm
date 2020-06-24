package com.zhouchen.application.sample.di.component

import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.application.sample.viewmodel.SampleListViewModel
import com.zhouchen.application.sample.viewmodel.SampleViewModel
import dagger.Component

@AppScope
@Component(
    dependencies = [AppSubcomponent::class]
)
interface SampleViewModelComponent {
    @Component.Builder
    interface Builder {
        fun appSubcomponent(subcomponent: AppSubcomponent): Builder
        fun build(): SampleViewModelComponent
    }

    fun inject(sampleListViewModel: SampleListViewModel)
    fun inject(sampleViewModel: SampleViewModel)
}