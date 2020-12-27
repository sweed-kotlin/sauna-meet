package com.sweed.saunameet.oildetails

import android.util.Log
import androidx.lifecycle.*
import com.sweed.saunameet.database.Oil
import com.sweed.saunameet.database.OilDatabaseDao
import kotlinx.coroutines.launch

class OilDetailsViewModel(
    dataSource: OilDatabaseDao,
    private val oilKey: Long
) : ViewModel() {

    val database = dataSource

    private val oil: LiveData<Oil>
    fun getOil() = oil

    private val _onBackToOverviewEvent = MutableLiveData<Boolean?>()
    val onBackToOverviewEvent: LiveData<Boolean?>
        get() = _onBackToOverviewEvent

    private val _onDeleteEvent = MutableLiveData<Oil?>()
    val onDeleteEvent: LiveData<Oil?>
        get() = _onDeleteEvent


    init {
        Log.i("init", "$oilKey")
        oil = database.get(oilKey)
    }

    fun onBack() {
        _onBackToOverviewEvent.value = true
    }

    fun onDelete() {
        Log.i("init", "${getOil().value}")

        var dumpOil = oil.value
        dumpOil?.let {
            viewModelScope.launch {
                database.delete(dumpOil)
            }
        }
        _onDeleteEvent.value = dumpOil

    }

    fun onFinishBackToOverview() {
        _onBackToOverviewEvent.value = null
    }
    fun onFinishDelete() {
        _onDeleteEvent.value = null
    }
}

class OilDetailsViewModelFactory(
    private val database: OilDatabaseDao,
    private val oilKey: Long,
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OilDetailsViewModel::class.java)) {
            return OilDetailsViewModel(database, oilKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}