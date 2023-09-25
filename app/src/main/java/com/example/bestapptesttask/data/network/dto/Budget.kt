package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Budget(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: Int
)