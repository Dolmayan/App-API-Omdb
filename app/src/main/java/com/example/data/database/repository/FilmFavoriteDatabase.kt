package com.example.data.database.repository

import com.example.data.database.entity.FilmFavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FilmFavoriteDatabase {

    suspend fun insertFilm(film: FilmFavoriteEntity): Long
    fun deleteFilm(id: String): Completable
    fun getAllFilms(): Single<List<FilmFavoriteEntity>>
    fun existFilm(id: String): Single<FilmFavoriteEntity>
}