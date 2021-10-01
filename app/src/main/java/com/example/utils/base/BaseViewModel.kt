package com.example.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    private val disposables = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()

    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun addDisposable(d: Disposable) {
        disposables.add(d)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}