package com.sweed.saunameet.session

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sweed.saunameet.R

const val DEFAULT_PROGRESS = 2

class StartSessionViewModel(val application: Application) : ViewModel() {


    //resources : Resources
    private val _selectedMood = MutableLiveData<String?>()
    val selectedMood: LiveData<String?>
        get() = _selectedMood

    private val _progress = MutableLiveData<Int?>()
    val progress: LiveData<Int?>
        get() = _progress

    private var _onNextButtonEvent = MutableLiveData<Boolean?>()
    val onNextButtonEvent: LiveData<Boolean?>
        get() = _onNextButtonEvent


    init {

        _progress.value = DEFAULT_PROGRESS
        selectionChanged(DEFAULT_PROGRESS)
    }

    fun onNextEvent() {
        _onNextButtonEvent.value = true
    }

    fun doneNavigating() {
        _onNextButtonEvent.value = null
    }


    fun selectionChanged(progress: Int) {
        _progress.value = progress
        _selectedMood.value = when (progress) {
            0 -> application.applicationContext.getString(R.string.sleepy_mood_string)
            1 -> application.applicationContext.getString(R.string.sad_mood_string)
            2 -> application.applicationContext.getString(R.string.neutral_mood_string)
            3 -> application.applicationContext.getString(R.string.content_mood_string)
            4 -> application.applicationContext.getString(R.string.excited_mood_string)
            else -> application.applicationContext.getString(R.string.neutral_mood_string)
        }
    }
}

class StartSessionViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartSessionViewModel::class.java)) {
            return StartSessionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}