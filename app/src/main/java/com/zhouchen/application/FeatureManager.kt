package com.zhouchen.application

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

object FeatureManager {
    private lateinit var mContext: Context
    private lateinit var manager: SplitInstallManager

    private val feature_sample by lazy { mContext.getString(R.string.dynamic_feature_scrolling) }
    private const val PACKAGE_NAME = "com.zhouchen.application"
    private const val PACKAGE_NAME_SAMPLE = "$PACKAGE_NAME.sample"
    private const val SAMPLE_CLASSNAME = "$PACKAGE_NAME_SAMPLE.activity.ScrollingActivity"

    fun init(ctx: Context){
        mContext = ctx
        manager = SplitInstallManagerFactory.create(mContext)
    }

    fun loadAndLaunchModule(activity: Activity, name: String) {
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
             onSuccessfulLoad(activity, name, launch = true)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        // Load and install the requested feature module.
        manager.startInstall(request)
    }

    /**
     * Define what to do once a feature module is loaded successfully.
     * @param moduleName The name of the successfully loaded module.
     * @param launch `true` if the feature module should be launched, else `false`.
     */
    private fun onSuccessfulLoad(activity: Activity, moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                feature_sample -> launchActivity(activity, SAMPLE_CLASSNAME)
            }
        }
    }

    /** Launch an activity by its class name. */
    private fun launchActivity(activity: Activity, className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        activity.startActivity(intent)
    }

}