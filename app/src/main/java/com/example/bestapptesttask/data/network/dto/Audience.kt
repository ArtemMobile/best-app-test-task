package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Audience(
    @SerializedName("count")
    val count: Int,
    @SerializedName("country")
    val country: String
)