package com.zhouchen.application.di.component

import android.app.Application
import com.zhouchen.application.App
import com.zhouchen.application.di.module.AppModule
import com.zhouchen.application.di.scopes.AppScope
import com.zhouchen.sdk.di.component.IAppComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : IAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    //TestAppComponent needs to define Mocked provider when necessary
    fun inject(app: App) //TestAppComponent extends from the AppComponent

}