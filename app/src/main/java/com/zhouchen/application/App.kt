package com.zhouchen.application

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.zhouchen.application.di.component.DaggerAppComponent
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.base.ui.IApp
import com.zhouchen.base.ui.LanguageHelper

open class App : Application(), IApp {
    private lateinit var appSubcomponent: AppSubcomponent
    open fun createComponent() {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appSubcomponent = appComponent.appSubcomponentBuilder.build()
        BaseActivity.setAppSubcomponent(appSubcomponent)
        appComponent.inject(this)
    }

    fun setAppSubcomponent(subcomponent: AppSubcomponent) {
        appSubcomponent = subcomponent
    }

    override fun getAppSubcomponent() : AppSubcomponent {
        return appSubcomponent
    }

    override fun onCreate() {
        createComponent()
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        LanguageHelper.init(base)
        FeatureManager.init(base)
        val ctx = LanguageHelper.getLanguageConfigurationContext(base)
        super.attachBaseContext(ctx)
        SplitCompat.install(this)
    }

}