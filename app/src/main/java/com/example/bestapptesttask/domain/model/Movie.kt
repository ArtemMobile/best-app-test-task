package com.example.bestapptesttask.domain.model

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