package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Premiere(
    @SerializedName("russia")
    val russia: String,
    @SerializedName("world")
    val world: String
)