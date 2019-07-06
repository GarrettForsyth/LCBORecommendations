package com.example.android.home.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.core.Event
import javax.inject.Inject

class HomeViewModel @Inject constructor(): ViewModel() {

    private val _browseCommand = MutableLiveData<Event<Unit>>()
    val browseCommand: LiveData<Event<Unit>> = _browseCommand

    fun browse() {
        _browseCommand.value = Event(Unit)
    }

}