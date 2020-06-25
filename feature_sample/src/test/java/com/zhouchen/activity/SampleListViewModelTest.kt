/*
 * Copyright (C) 2017 The Android Open Source Project
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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zhouchen.access.AccessImpl
import com.zhouchen.application.sample.activity.SampleListAdapter
import com.zhouchen.application.sample.viewmodel.SampleListViewModel
import com.zhouchen.datalayer.model.Sample
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


/**
 * Unit test for [SampleListViewModel]
 */
class SampleListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataSource: AccessImpl

    @Mock
    private lateinit var adapter: SampleListAdapter

    private lateinit var viewModel: SampleListViewModel
    private val fakeList : ArrayList<Sample> = ArrayList()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fakeList.add(Sample(1, "title", "time", "ingress", "image", 1.0, 1.0))
        viewModel = SampleListViewModel(dataSource, adapter)
    }

    @Test
    fun getUserName_whenNoUserSaved() {
        // Given that the UserDataSource returns test samples
        `when`(dataSource.getSamples()).thenReturn(Observable.fromCallable { fakeList })
        doNothing().`when`(adapter).updateSampleList(fakeList)
        //When getting the user name
        viewModel.loadSamples()
        verify(adapter, times(1)).updateSampleList(fakeList)
    }

}
