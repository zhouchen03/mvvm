/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhouchen.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.activity.viewModels
import com.zhouchen.application.R
import com.zhouchen.application.databinding.ActivityLivedataBinding
import com.zhouchen.application.di.component.DaggerLiveDataActivityComponent
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.base.ui.IApp
import com.zhouchen.viewmodel.LiveDataVMFactory
import com.zhouchen.viewmodel.LiveDataViewModel
import javax.inject.Inject

class LiveDataActivity : BaseActivity() {
    @Inject
    lateinit var mApp : IApp

    // Obtain ViewModel
    private val viewmodel: LiveDataViewModel by viewModels { LiveDataVMFactory(mApp) }

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
}


