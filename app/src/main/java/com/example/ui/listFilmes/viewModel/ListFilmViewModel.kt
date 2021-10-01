package com.example.ui.listFilmes.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.data.business.FilmsRepositoryImpl
import com.example.data.response.FilmDTO
import com.example.data.response.SimpleFilm
import com.example.utils.base.BaseViewModel

class ListFilmViewModel(val api: FilmsRepositoryImpl) : BaseViewModel(), LifecycleObserver {

    val filmesList = MutableLiveData<FilmDTO>()
    val filmesName = MutableLiveData<String>()
    val msgError = MutableLiveData<String>()
    val closeKeyboard = MutableLiveData<Boolean>()

    var page: Int = 0

    private fun searchMovies() {

        addDisposable(
            api.getFilmes(filmeName = filmesName.value ?: "", page = page)
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({
                    if (page == 1) {
                        filmesList.postValue(it)
                    } else {
                        val list: List<SimpleFilm> = filmesList.value!!.search.plus(it.search)
                        filmesList.postValue(
                            FilmDTO(
                                search = list,
                                response = filmesList.value!!.response,
                                totalResults = filmesList.value!!.totalResults
                            )
                        )
                    }
                },
                    {
                        msgError.postValue("Filme não encontrado")
                    })
        )
    }

    fun nextPage() {
        page++
        searchMovies()
    }

    fun search() {
        page = 1
        closeKeyboard.postValue(false)
        searchMovies()
    }
}