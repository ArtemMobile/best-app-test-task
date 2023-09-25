package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("spoiler")
    val spoiler: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)