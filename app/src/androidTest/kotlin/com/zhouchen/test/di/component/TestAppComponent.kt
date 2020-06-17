package com.zhouchen.test.di.component

import android.app.Application
import com.zhouchen.application.di.component.AppComponent
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.test.TestApp
import com.zhouchen.test.di.module.TestAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [AndroidInjectionModule::class, TestAppModule::class])
interface TestAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): TestAppComponent
    }

    // member injections

    //TestAppComponent extends from the AppComponent
    //TestAppComponent needs to define Mocked provider when necessary

    fun inject(app: TestApp)
}