package com.zhouchen.test

import com.zhouchen.application.App
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.test.di.component.DaggerTestAppComponent

class TestApp : App() {

    override fun createComponent() {
        val testappComponent = DaggerTestAppComponent
            .builder()
            .application(this)
            .build()
        setAppSubcomponent(testappComponent.appSubcomponentBuilder.build())
        BaseActivity.setAppSubcomponent(getAppSubcomponent())
        testappComponent.inject(this)
    }
}