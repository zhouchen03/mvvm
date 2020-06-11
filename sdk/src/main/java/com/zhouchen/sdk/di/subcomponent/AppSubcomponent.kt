package com.zhouchen.sdk.di.subcomponent

import android.content.Context
import dagger.Subcomponent

@Subcomponent
interface AppSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AppSubcomponent
    }

    // The following injections exposed by dagger 2 provisions from app
    val ApplicationContext: Context
}