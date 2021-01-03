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

    private var oil: LiveData<Oil>
    fun getOil() = oil

    private val _onBackToOverviewEvent = MutableLiveData<Boolean?>()
    val onBackToOverviewEvent: LiveData<Boolean?>
        get() = _onBackToOverviewEvent

    private val _onDeleteEvent = MutableLiveData<Oil?>()
    val onDeleteEvent: LiveData<Oil?>
        get() = _onDeleteEvent


    init {
        oil = database.get(oilKey)
    }

    fun onBack() {
        _onBackToOverviewEvent.value = true
    }

     fun onFavorite() {
         var favOil = oil.value


         favOil?.let {
             favOil.favorite = favOil.favorite.not()
             viewModelScope.launch {
                 database.update(favOil)
                 oil = database.get(oilKey)

             }
         }
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