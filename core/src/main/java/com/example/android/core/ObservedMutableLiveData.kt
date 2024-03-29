package com.example.android.core

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData

/**
 * A MutableLiveData that will update if its observed data's Observed fields change.
 */
class ObservedMutableLiveData<T: BaseObservable> : MutableLiveData<T>() {

    override fun setValue(value: T) {
        super.setValue(value)
        value.addOnPropertyChangedCallback(callback)
    }

    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            setValue(value!!)
        }

    }
}

