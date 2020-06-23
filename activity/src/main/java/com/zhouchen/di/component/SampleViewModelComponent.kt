package com.zhouchen.di.component

import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.viewmodel.SampleListViewModel
import com.zhouchen.viewmodel.SampleViewModel
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