package com.zhouchen.test.di.component

import com.zhouchen.application.di.component.AppComponent
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.repository.di.module.AccessModule
import com.zhouchen.test.TestApp
import com.zhouchen.test.di.module.TestAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [AndroidInjectionModule::class,
    TestAppModule::class,
    AccessModule::class])
interface TestAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: com.zhouchen.test.TestApp): Builder
        fun build(): TestAppComponent
    }

    // member injections

    //TestAppComponent extends from the AppComponent
    //TestAppComponent needs to define Mocked provider when necessary

    fun inject(app: com.zhouchen.test.TestApp)
}