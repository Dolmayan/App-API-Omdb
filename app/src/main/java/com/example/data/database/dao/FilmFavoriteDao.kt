package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.FilmFavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FilmFavoriteDAO {

    @Query("SELECT * FROM Film")
    fun getAll(): Single<List<FilmFavoriteEntity>>

    @Insert
    fun insert(subscriber: FilmFavoriteEntity): Long

    @Query("DELETE FROM Film WHERE imdbID = :id")
    fun delete(id: String): Completable

    @Query("SELECT * FROM Film WHERE imdbID = :id")
    fun exist(id: String): Single<FilmFavoriteEntity>
}