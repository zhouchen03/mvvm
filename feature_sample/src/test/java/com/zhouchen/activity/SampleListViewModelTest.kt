package com.zhouchen.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zhouchen.access.AccessImpl
import com.zhouchen.application.sample.activity.SampleListAdapter
import com.zhouchen.application.sample.viewmodel.SampleListViewModel
import com.zhouchen.datalayer.model.Sample
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test import org.mockito.ArgumentMatchers
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

    private val fakeList : ArrayList<Sample> = ArrayList()

    @Before
    fun setUp() {
        //init mock
        MockitoAnnotations.initMocks(this)
        fakeList.add(Sample(1, "title", "time", "ingress", "image", 1.0, 1.0))
    }

    @Test
    fun test_updateSampleList() {
        val viewModel = spy(SampleListViewModel(dataSource, adapter))
        // Given that the UserDataSource returns test samples
        `when`(dataSource.getSamples()).thenReturn(Observable.fromCallable { fakeList })
        doNothing().`when`(adapter).updateSampleList(anyList())

        viewModel.loadSamples()
        verify(viewModel, times(1)).onRetrieveSampleListSuccess(fakeList)
    }

}
