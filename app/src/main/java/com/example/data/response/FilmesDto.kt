package com.example.data.response

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("Search") val search: List<SimpleFilm>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)

data class SimpleFilm(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Poster") val poster: String
)