package com.waysnpaths.core.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class CoreViewModel<Model> : ViewModel() {

    protected val disposables = CompositeDisposable()

    protected val model by lazy { MutableLiveData<Model>() }

    fun getModel(): LiveData<Model> {
        return model
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}