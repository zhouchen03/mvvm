package com.zhouchen.application

import android.app.Application
import com.zhouchen.application.di.component.DaggerAppComponent
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.base.ui.IApp

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

    override fun getAppSubcomponent() : AppSubcomponent {
        return appSubcomponent
    }

    override fun onCreate() {
        createComponent()
        super.onCreate()
    }

}