package com.zhouchen.sdk.di.component;

import com.zhouchen.sdk.di.subcomponent.AppSubcomponent;

public interface IAppComponent {

    AppSubcomponent.Builder getAppSubcomponentBuilder();

}
