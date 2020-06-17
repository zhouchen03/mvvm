package com.zhouchen.application.di.module

import android.content.Context
import com.zhouchen.application.App
import com.zhouchen.application.Config
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.datalayer.api.IConfig
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.base.ui.IApp
import dagger.Module
import dagger.Provides

/**
 * Module for dependency injection
 */
@Module(
    subcomponents = [AppSubcomponent::class]
)
class AppModule {
    @Provides
    @AppScope
    fun provideContext(application: App): Context {
        return application.applicationContext
    }
    @Provides
    @AppScope
    fun provideApp(application: App): IApp {
        return application
    }

    @Provides
    @AppScope
    fun provideConfig(context: Context): IConfig {
        return Config(context)
    }
}