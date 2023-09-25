package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("teasers")
    val teasers: List<Any>,
    @SerializedName("trailers")
    val trailers: List<Trailer>
)