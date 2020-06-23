package com.zhouchen.test

import com.zhouchen.application.App
import com.zhouchen.test.di.component.DaggerTestAppComponent

class TestApp : App() {

    override fun createComponent() {
        DaggerTestAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}