package com.zhouchen.application

import android.content.Context
import com.zhouchen.datalayer.api.IConfig

class Config(val context: Context) : IConfig {
    private val BASE_URL: String = "https://www.apphusetreach.no/application/119267/"

    override fun getBaseURL(): String {
        return BASE_URL
    }
}