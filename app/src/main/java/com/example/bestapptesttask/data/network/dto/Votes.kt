package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Votes(
    @SerializedName("await")
    val await: Int,
    @SerializedName("filmCritics")
    val filmCritics: Int,
    @SerializedName("imdb")
    val imdb: Int,
    @SerializedName("kp")
    val kp: Int,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Int
)