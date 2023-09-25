package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("name")
    val name: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    val url: String
)