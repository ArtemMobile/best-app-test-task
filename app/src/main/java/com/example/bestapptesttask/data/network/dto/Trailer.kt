package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)