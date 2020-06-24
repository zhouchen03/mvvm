package com.zhouchen.base.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.zhouchen.base.di.subcomponent.AppSubcomponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }
    companion object {
        private lateinit var mAppSubcomponent: AppSubcomponent

        @JvmStatic
        fun getAppSubcomponent(): AppSubcomponent {
            return mAppSubcomponent
        }

        fun setAppSubcomponent(subcomponent: AppSubcomponent) {
            mAppSubcomponent = subcomponent
        }
    }

    abstract fun createComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        // inject as a subcomponent
        createComponent()
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        val ctx = LanguageHelper.getLanguageConfigurationContext(newBase)
        super.attachBaseContext(ctx)
        SplitCompat.installActivity(this)
    }
}