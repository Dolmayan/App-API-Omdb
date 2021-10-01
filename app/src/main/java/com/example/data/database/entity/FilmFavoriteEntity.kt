package com.example.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Film")
data class FilmFavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "Year") val year: String?,
    @ColumnInfo(name = "Rated") val rated: String?,
    @ColumnInfo(name = "Released") val released: String?,
    @ColumnInfo(name = "Runtime") val runtime: String?,
    @ColumnInfo(name = "Genre") val genre: String?,
    @ColumnInfo(name = "Director") val director: String?,
    @ColumnInfo(name = "Writer") val writer: String?,
    @ColumnInfo(name = "Actors") val actors: String?,
    @ColumnInfo(name = "Plot") val plot: String?,
    @ColumnInfo(name = "Language") val language: String?,
    @ColumnInfo(name = "Country") val country: String?,
    @ColumnInfo(name = "Awards") val awards: String?,
    @ColumnInfo(name = "Poster") val poster: String?,
    @ColumnInfo(name = "Metascore") val metascore: String?,
    @ColumnInfo(name = "imdbRating") val imdbRating: String?,
    @ColumnInfo(name = "imdbVotes") val imdbVotes: String?,
    @ColumnInfo(name = "imdbID") val imdbID: String?,
    @ColumnInfo(name = "Type") val type: String?,
    @ColumnInfo(name = "DVD") val dvd: String?,
    @ColumnInfo(name = "BoxOffice") val boxOffice: String?,
    @ColumnInfo(name = "Production") val production: String?,
    @ColumnInfo(name = "Website") val website: String?,
    @ColumnInfo(name = "Response") val response: String?
)
