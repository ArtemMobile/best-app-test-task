package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class World(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: Int
)