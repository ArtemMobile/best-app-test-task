package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class Distributors(
    @SerializedName("distributor")
    val distributor: String,
    @SerializedName("distributorRelease")
    val distributorRelease: String
)