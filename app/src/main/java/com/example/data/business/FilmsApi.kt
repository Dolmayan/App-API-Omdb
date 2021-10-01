package com.example.data.business

import com.example.data.response.FilmDTO
import com.example.data.response.FilmesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {

    @GET("?apikey=7fbd705d")
    fun getFilmes(@Query("s") title: String?, @Query("page") page: Int): Single<FilmDTO>

    @GET("?apikey=7fbd705d")
    fun getFilmForId(@Query("i") id: String?): Single<FilmesListResponse>
}