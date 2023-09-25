package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("name")
    val name: String,
    @SerializedName("nameEn")
    val nameEn: String
)