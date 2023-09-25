package com.example.bestapptesttask.data.network.dto

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String
)