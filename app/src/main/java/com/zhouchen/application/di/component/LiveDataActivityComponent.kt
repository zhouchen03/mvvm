package com.zhouchen.application.di.component

import com.zhouchen.activity.LiveDataActivity
import com.zhouchen.base.di.scopes.ActivityScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@ActivityScope
@Component(
    modules = [AndroidInjectionModule::class],
    dependencies = [AppSubcomponent::class]
)
interface LiveDataActivityComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: LiveDataActivity): Builder
        fun appSubcomponent(subcomponent: AppSubcomponent): Builder
        fun build(): LiveDataActivityComponent
    }

    fun inject(activity: LiveDataActivity)

}