package com.zhouchen.application

import android.app.Application
import com.zhouchen.application.di.component.DaggerAppComponent
import com.zhouchen.sdk.ui.BaseActivity

open class App : Application() {

    open fun createComponent() {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        BaseActivity.setAppSubcomponent(appComponent.appSubcomponentBuilder.build())
        appComponent.inject(this)
    }

    override fun onCreate() {
        createComponent()
        super.onCreate()
    }

}