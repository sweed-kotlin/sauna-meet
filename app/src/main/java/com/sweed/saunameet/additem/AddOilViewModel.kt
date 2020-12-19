package com.sweed.saunameet.additem

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.sweed.saunameet.database.Oil
import com.sweed.saunameet.database.OilDatabaseDao
import kotlinx.coroutines.launch

class AddOilViewModel(
    val database: OilDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    var oil = MutableLiveData<Oil?>()

    private val _oilName = MutableLiveData<String?>()
    val oilName: LiveData<String?>
        get() = _oilName

    private val _oilRating = MutableLiveData<Float?>()
    val oilRating: LiveData<Float?>
        get() = _oilRating


    init {
        oil.value = Oil(name = "", rating = 0.0F)
    }
    val addButtonVisible = Transformations.map(oilName) {
        !it.isNullOrEmpty() && _oilRating.value?:0F > 0F
    }

    private var _addNewOilEvent = MutableLiveData<Oil>()
    val addNewOilEvent: LiveData<Oil>
        get() = _addNewOilEvent


    fun doneNavigating() {
        _addNewOilEvent.value = null
    }

    fun onAddNewOil() {
        viewModelScope.launch {

            val oilName = _oilName.value?.trim() ?: return@launch
            val oilRating = _oilRating.value ?: 0F

            var newOil = Oil(name = oilName, rating = oilRating)
            database.insert(newOil)
            _addNewOilEvent.value = database.getOilByName(newOil.name)
        }
    }


    fun onChangeName(oilName: String) {
        _oilName.value = oilName
    }

    fun onChangeRating(rating: Float) {
        _oilRating.value = rating
    }
}


class AddOilViewModelFactory(
    private val database: OilDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    //    private val sleepNightKey: Long,
//    private val dataSource: SleepDatabaseDao
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddOilViewModel::class.java)) {
            return AddOilViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}