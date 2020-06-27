package com.zhouchen.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.activity.viewModels
import com.zhouchen.application.FeatureManager
import com.zhouchen.application.R
import com.zhouchen.application.databinding.ActivityLivedataBinding
import com.zhouchen.application.di.component.DaggerLiveDataActivityComponent
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.viewmodel.LiveDataVMFactory
import com.zhouchen.viewmodel.LiveDataViewModel
import javax.inject.Inject

class LiveDataActivity : BaseActivity() {
    @Inject
    lateinit var mApp : IApp

    @Inject
    lateinit var mAccess: IAccess

    // Obtain ViewModel
    private val viewmodel: LiveDataViewModel by viewModels {
        LiveDataVMFactory(
            mAccess
        )
    }

    override fun createComponent() {
        DaggerLiveDataActivityComponent
            .builder()
            .appSubcomponent(getAppSubcomponent())
            .activity(this)
            .build()
            .inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain binding object using the Data Binding library
        val binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(
            this, R.layout.activity_livedata
        )

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this

        // Bind ViewModel
        binding.viewmodel = viewmodel
    }

    fun launchSampleFeature() {
        FeatureManager.loadAndLaunchModule(this, getString(R.string.dynamic_feature_scrolling))
    }
}


