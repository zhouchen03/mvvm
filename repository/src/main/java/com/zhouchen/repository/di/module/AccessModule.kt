package com.zhouchen.repository.di.module

import com.zhouchen.access.AccessImpl
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.repository.di.subcomponent.AccessSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Provider

/**
 * Module which provides all required dependencies about network
 */

@Module(
    subcomponents = [AccessSubcomponent::class]
)
class AccessModule {
    @Provides
    internal fun provideAccess(provider: Provider<AccessSubcomponent.Builder>): IAccess {
        return AccessImpl(provider)
    }
}