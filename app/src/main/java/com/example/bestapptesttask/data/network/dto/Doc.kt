package com.example.bestapptesttask.data.network.dto

import com.google.gson.annotations.SerializedName

data class Doc(
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("description")
    val description: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("externalId")
    val externalId: ExternalId,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("votes")
    val votes: Votes,
    @SerializedName("watchability")
    val watchability: Watchability,
    @SerializedName("year")
    val year: Int
)