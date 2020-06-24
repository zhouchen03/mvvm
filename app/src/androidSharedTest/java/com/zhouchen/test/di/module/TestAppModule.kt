package com.zhouchen.test.di.module

import android.content.Context
import com.zhouchen.application.Config
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.api.IConfig
import com.zhouchen.test.TestApp
import dagger.Module
import dagger.Provides

/**
 * Module for dependency injection
 */
@Module(
    subcomponents = [AppSubcomponent::class]
)
class TestAppModule {
    @Provides
    @AppScope
    fun provideContext(application: com.zhouchen.test.TestApp): Context {
        return application.applicationContext
    }

    @Provides
    @AppScope
    fun provideApp(application: com.zhouchen.test.TestApp): IApp {
        return application
    }

    @Provides
    @AppScope
    fun provideConfig(context: Context): IConfig {
        return Config(context)
    }
}