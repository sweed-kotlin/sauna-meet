package com.sweed.saunameet.allIOils

import android.app.Application
import androidx.lifecycle.*
import com.sweed.saunameet.R
import com.sweed.saunameet.ResourcesProvider
import com.sweed.saunameet.database.Oil
import com.sweed.saunameet.database.OilDatabaseDao
import kotlinx.coroutines.launch

class AllOilsViewModel(
    val database: OilDatabaseDao,
    application: Application
): AndroidViewModel(application) {

    val oils = database.getAllOils()

    private val rp = ResourcesProvider.getInstance(application.applicationContext)

    private val _navigateToOilDetail = MutableLiveData<Long>()
    val navigateToOilDetail
        get() = _navigateToOilDetail


    fun initializeDatabase() {
        if (oils.value.isNullOrEmpty()){
            viewModelScope.launch {
                database.insert(Oil(name = rp.getString(R.string.new_item_text), rating = 5.0F, isAddButton = true))
            }
        }
    }

    fun onOilClicked(id : Long) {
        _navigateToOilDetail.value = id
    }

    fun onOilDetailNavigated() {
        _navigateToOilDetail.value = null
    }


}

class AllOilsViewModelFactory(
    private val database: OilDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllOilsViewModel::class.java)) {
            return AllOilsViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}