package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdropsCount")
    val backdropsCount: Int,
    @SerializedName("framesCount")
    val framesCount: Int,
    @SerializedName("postersCount")
    val postersCount: Int
)