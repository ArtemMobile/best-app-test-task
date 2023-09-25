package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Backdrop(
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    val url: String
)