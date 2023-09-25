package com.example.bestapptesttask.domain.model

import com.example.bestapptesttask.data.network.dto.PersonDTO


data class Movie(
    val id: Int,
    val image: String,
    val title: String,
    val plot: String,
    val rating: Double,
    val releaseDate: Int,
    var actorList: List<Person>?,
    val votes: Int,
    val trailer: String?
)