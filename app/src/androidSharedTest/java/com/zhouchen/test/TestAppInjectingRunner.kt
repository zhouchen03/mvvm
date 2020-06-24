package com.zhouchen.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import kotlin.jvm.Throws

class TestAppInjectingRunner : AndroidJUnitRunner() {
    @Override
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, "com.zhouchen.test.TestApp", context)
    }
}