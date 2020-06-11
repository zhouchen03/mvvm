package com.zhouchen.sdk.ui

import androidx.appcompat.app.AppCompatActivity
import com.zhouchen.sdk.di.subcomponent.AppSubcomponent
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
}