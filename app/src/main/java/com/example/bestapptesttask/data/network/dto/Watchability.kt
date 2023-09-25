package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Watchability(
    @SerializedName("items")
    val items: List<Item>
)