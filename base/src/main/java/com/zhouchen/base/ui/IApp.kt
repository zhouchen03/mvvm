package com.zhouchen.base.ui

import com.zhouchen.base.di.subcomponent.AppSubcomponent

interface IApp {
    fun getAppSubcomponent() : AppSubcomponent
}