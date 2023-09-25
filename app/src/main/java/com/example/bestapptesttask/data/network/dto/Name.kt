package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)