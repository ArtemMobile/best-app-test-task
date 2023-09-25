package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class PersonDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("enProfession")
    val enProfession: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("profession")
    val profession: String
)