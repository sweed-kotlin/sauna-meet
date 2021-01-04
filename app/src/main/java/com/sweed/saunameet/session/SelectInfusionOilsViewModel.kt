package com.sweed.saunameet.session

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectInfusionOilsViewModel : ViewModel() {
    private var _onStartButtonEvent = MutableLiveData<Boolean?>()
    val onStartButtonEvent: LiveData<Boolean?>
        get() = _onStartButtonEvent


    fun onClickHaXX(v: View) {
        _onStartButtonEvent.value = true
    }

    fun onStartEvent() {
        _onStartButtonEvent.value = true
    }

    fun doneNavigating() {
        _onStartButtonEvent.value = null
    }
}