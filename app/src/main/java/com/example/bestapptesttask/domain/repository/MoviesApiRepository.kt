package com.example.bestapptesttask.domain.repository

import com.example.bestapptesttask.data.Resource
import com.example.bestapptesttask.data.network.dto.MoviesResponseDTO
import com.example.bestapptesttask.data.network.dto.SingleMovieResponseDTO
import com.example.bestapptesttask.domain.model.Movie

interface MoviesApiRepository {
    suspend fun getMovies(): Resource<MoviesResponseDTO>

    suspend fun getMovieById(id: Int): Resource<Movie>
}