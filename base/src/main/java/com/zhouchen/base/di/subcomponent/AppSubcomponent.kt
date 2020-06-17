package com.zhouchen.base.di.subcomponent

import android.content.Context
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.api.IConfig
import dagger.Subcomponent

@Subcomponent
interface AppSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AppSubcomponent
    }

    // The following injections exposed by dagger 2 provisions from app
    val getApplicationContext: Context
    val getApp: IApp
    val getConfig: IConfig
    val getAccess: IAccess
}