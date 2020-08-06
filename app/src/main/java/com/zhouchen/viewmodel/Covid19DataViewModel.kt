package com.zhouchen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhouchen.datalayer.api.IAccess
import com.zhouchen.datalayer.model.DbCovid19Data
import com.zhouchen.datalayer.model.Success
import com.zhouchen.event.Event
import com.zhouchen.network.cancelIfActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Covid19DataViewModel(private val access: IAccess) : ViewModel() {
    private val statusMessage = MutableLiveData<Event<String>>()
    private var getCovid19DataJob: Job? = null

    val message : LiveData<Event<String>>
        get() = statusMessage

    fun getCovid19Data(zip: String, daysInPast: Int) {
        getCovid19DataJob.cancelIfActive()
        getCovid19DataJob = viewModelScope.launch {
            access.getCovid19Data(zip, daysInPast)
                .collect {
                    when (it) {
                        is Success<DbCovid19Data> -> {
                            statusMessage.value = Event("County: ${it.data.counties[0].countyName}")
                        }
                        is Error -> {
                        }
                        is Process -> {

                        }
                    }
                }
        }
    }
}