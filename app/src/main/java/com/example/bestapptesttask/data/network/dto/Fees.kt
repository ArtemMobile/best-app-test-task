package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Fees(
    @SerializedName("russia")
    val russia: Russia,
    @SerializedName("usa")
    val usa: Usa,
    @SerializedName("world")
    val world: World
)