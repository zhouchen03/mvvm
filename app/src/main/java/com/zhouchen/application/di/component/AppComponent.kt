package com.zhouchen.application.di.component

import com.zhouchen.repository.di.module.AccessModule
import com.zhouchen.application.App
import com.zhouchen.application.di.module.AppModule
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.component.IAppComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    AccessModule::class])
interface AppComponent : IAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

    //TestAppComponent needs to define Mocked provider when necessary
    fun inject(app: App) //TestAppComponent extends from the AppComponent

}