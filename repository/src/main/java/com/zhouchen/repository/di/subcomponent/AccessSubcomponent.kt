package com.zhouchen.repository.di.subcomponent

import com.zhouchen.access.AccessImpl
import com.zhouchen.repository.di.module.DatabaseModule
import com.zhouchen.repository.di.module.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [DatabaseModule::class,
                NetworkModule::class]
)
interface AccessSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AccessSubcomponent
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
    }

    fun inject(access: AccessImpl)
}