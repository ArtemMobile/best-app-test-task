package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class ExternalId(
    @SerializedName("imdb")
    val imdb: String,
    @SerializedName("kpHD")
    val kpHD: String,
    @SerializedName("tmdb")
    val tmdb: Int
)