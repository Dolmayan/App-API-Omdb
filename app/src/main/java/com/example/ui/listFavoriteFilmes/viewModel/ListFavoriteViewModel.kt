package com.example.ui.listFavoriteFilmes.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.entity.FilmFavoriteEntity
import com.example.data.database.repository.DatabaseDataSource
import com.example.utils.base.BaseViewModel
import kotlinx.coroutines.launch

class ListFavoriteViewModel(private val repository: DatabaseDataSource) : BaseViewModel(),
    LifecycleObserver {

    var films: MutableLiveData<List<FilmFavoriteEntity>> = MutableLiveData()

    fun getAllFilms() = viewModelScope.launch {

        addDisposable(
            repository.getAllFilms()
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({
                    films.postValue(it)
                },
                    {
                        println("Deu ruim")
                    })
        )
    }
}