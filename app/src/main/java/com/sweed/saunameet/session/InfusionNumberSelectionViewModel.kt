package com.sweed.saunameet.session

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Number Picker Ref>
// zoftino.com/android-number-picker-tutorial

//https://stackoverflow.com/questions/17944061/how-to-use-number-picker-with-dialog
//https://stackoverflow.com/questions/17944061/how-to-use-number-picker-with-dialog#17944168

// TODO open in dialogue instead fragment
// dialog> https://stackoverflow.com/questions/17944061/how-to-use-number-picker-with-dialog#17944168
class InfusionNumberSelectionViewModel : ViewModel() {


    private var _onNextButtonEvent = MutableLiveData<Boolean>()
    val onNextButtonEvent: LiveData<Boolean>
        get() = _onNextButtonEvent

    fun onNextEvent2() {
        Log.i("button","execute!")
        _onNextButtonEvent.value = true
    }

    fun doneNavigating() {
        _onNextButtonEvent.value = null
    }


}

class InfusionNumberSelectionViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfusionNumberSelectionViewModel::class.java)) {
            return InfusionNumberSelectionViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}