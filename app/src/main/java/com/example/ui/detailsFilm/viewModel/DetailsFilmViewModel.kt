package com.example.ui.detailsFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.data.business.FilmsRepositoryImpl
import com.example.data.database.repository.DatabaseDataSource
import com.example.data.response.FilmesListResponse
import com.example.utils.base.BaseViewModel

class DetailsFilmViewModel(
    //private val database: DatabaseDataSource,
    private val api: FilmsRepositoryImpl
) : BaseViewModel(), LifecycleObserver {

    var response: MutableLiveData<FilmesListResponse> = MutableLiveData()
    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailsFilm(id: String) {
        addDisposable(
            api.getFilmForId(id)
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({
                    response.postValue(it)
                },
                    {
                        println("Deu ruim")
                    })
        )
    }

//    fun getFilm(id: String) {
//        addDisposable(
//            database.existFilm(id)
//                .doOnSubscribe { isLoading.postValue(true) }
//                .doFinally { isLoading.postValue(false) }
//                .subscribe({
//                    if (it == null) favorite.postValue(false)
//                    else favorite.postValue(true)
//                },{
//                    println("Deu ruim")
//                })
//        )
//    }
//
//    fun delete(id: String)  {
//        addDisposable(
//            database.deleteFilm(id)
//                .doOnSubscribe { isLoading.postValue(true) }
//                .doFinally { isLoading.postValue(false) }
//                .subscribe({
//
//                },{
//                    println("Deu ruim")
//                })
//        )
//    }

//    fun add(id: String) = viewModelScope.launch {
//        try {
//            val response = database.insertFilm(
//                FilmFavoriteEntity(
//                    title = response.value?.title,
//                    year = response.value?.year,
//                    rated = response.value?.rated,
//                    released = response.value?.released,
//                    runtime = response.value?.runtime,
//                    genre = response.value?.genre,
//                    director = response.value?.director,
//                    writer = response.value?.writer,
//                    actors = response.value?.actors,
//                    plot = response.value?.plot,
//                    language = response.value?.language,
//                    country = response.value?.country,
//                    awards = response.value?.awards,
//                    poster = response.value?.poster,
//                    metascore = response.value?.metascore,
//                    imdbRating = response.value?.imdbRating,
//                    imdbVotes = response.value?.imdbVotes,
//                    imdbID = response.value?.imdbID,
//                    type = response.value?.type,
//                    dvd = response.value?.dvd,
//                    boxOffice = response.value?.boxOffice,
//                    production = response.value?.production,
//                    website = response.value?.website,
//                    response = response.value?.response
//                )
//            )
//            if (response == null) {
//                favorite.postValue(false)
//            } else {
//                favorite.postValue(true)
//            }
//        } catch (ex: Exception) {
//            Log.e(ContentValues.TAG, ex.toString())
//        }
//    }
}