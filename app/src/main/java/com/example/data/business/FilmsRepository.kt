package com.example.data.business

import com.example.data.response.FilmDTO
import com.example.data.response.FilmesListResponse
import io.reactivex.Single

interface FilmsRepository {

    fun getFilmes(filmeName:String?, page:Int): Single<FilmDTO>
    fun getFilmForId(id:String?): Single<FilmesListResponse>
}