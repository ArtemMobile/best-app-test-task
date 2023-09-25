package com.example.bestapptesttask.data.network.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    @SerializedName("docs")
    val docs: List<Doc>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
)