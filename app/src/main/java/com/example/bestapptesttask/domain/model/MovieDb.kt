package com.example.bestapptesttask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDb(
    @PrimaryKey
    val id: Int,
    val rating: Double,
    val image: String,
    val title: String,
    var bookmarked: Boolean = false,
    val votes: Int
)