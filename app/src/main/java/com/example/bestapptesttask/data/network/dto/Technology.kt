package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Technology(
    @SerializedName("has3D")
    val has3D: Boolean,
    @SerializedName("hasImax")
    val hasImax: Boolean
)