package com.zhouchen.test.di.module

import android.app.Application
import android.content.Context
import com.zhouchen.base.di.scopes.AppScope
import com.zhouchen.base.di.subcomponent.AppSubcomponent
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
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}