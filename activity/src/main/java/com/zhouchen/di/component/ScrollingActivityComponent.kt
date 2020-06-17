package com.zhouchen.di.component

import com.zhouchen.activity.ScrollingActivity
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
interface ScrollingActivityComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: ScrollingActivity): Builder
        fun appSubcomponent(subcomponent: AppSubcomponent): Builder
        fun build(): ScrollingActivityComponent
    }

    fun inject(activity: ScrollingActivity)

}