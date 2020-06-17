package com.zhouchen.base.di.component;

import com.zhouchen.base.di.subcomponent.AppSubcomponent;

public interface IAppComponent {

    AppSubcomponent.Builder getAppSubcomponentBuilder();

}
