package com.zhouchen.application.di.component

import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.application.sample.viewmodel.LiveDataViewModel
import dagger.Component

@AppScope
@Component(
    dependencies = [AppSubcomponent::class]
)
interface LiveDataViewModelComponent {
    @Component.Builder
    interface Builder {
        fun appSubcomponent(subcomponent: AppSubcomponent): Builder
        fun build(): LiveDataViewModelComponent
    }

    fun inject(viewModel: LiveDataViewModel)
}