package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)