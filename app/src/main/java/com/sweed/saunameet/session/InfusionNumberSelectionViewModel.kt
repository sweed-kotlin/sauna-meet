package com.sweed.saunameet.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

    fun onNextEvent() {
        _onNextButtonEvent.value = true
    }

    fun doneNavigating() {
        _onNextButtonEvent.value = null
    }


}