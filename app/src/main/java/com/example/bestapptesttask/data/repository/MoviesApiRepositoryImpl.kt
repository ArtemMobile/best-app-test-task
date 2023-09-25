package com.example.bestapptesttask.data.repository

import com.example.bestapptesttask.data.Resource
import com.example.bestapptesttask.data.mapper.MovieMapper
import com.example.bestapptesttask.data.network.dto.MoviesResponseDTO
import com.example.bestapptesttask.data.network.dto.SingleMovieResponseDTO
import com.example.bestapptesttask.data.network.service.MoviesApiService
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.model.Person
import com.example.bestapptesttask.domain.repository.MoviesApiRepository
import javax.inject.Inject

class MoviesApiRepositoryImpl @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val movieMapper: MovieMapper
) :
    MoviesApiRepository {
    override suspend fun getMovies(): Resource<MoviesResponseDTO> {
        return try {
            val moviesResponse = moviesApiService.getMovies()

            return if (moviesResponse.isSuccessful) {
                Resource.Success(moviesResponse.body()!!)
            } else {
                Resource.Error(moviesResponse.errorBody().toString())
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty())
        }
    }

    override suspend fun getMovieById(id: Int): Resource<Movie> {
        return try {
            val movieResponse = moviesApiService.getMovieById(id)
            return if (movieResponse.isSuccessful && movieResponse.body() != null) {
                Resource.Success(movieMapper.mapDtoModelToEntity(movieResponse.body()!!))
            } else {
                Resource.Error(movieResponse.errorBody().toString())
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty())
        }
    }
}