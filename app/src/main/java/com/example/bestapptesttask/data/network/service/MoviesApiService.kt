package com.example.bestapptesttask.data.network.service

import com.example.bestapptesttask.data.network.ApiConstants.API_HEADER
import com.example.bestapptesttask.data.network.ApiConstants.API_KEY
import com.example.bestapptesttask.data.network.ApiConstants.ID
import com.example.bestapptesttask.data.network.ApiConstants.MOVIE
import com.example.bestapptesttask.data.network.ApiConstants.MOVIES
import com.example.bestapptesttask.data.network.dto.MoviesResponseDTO
import com.example.bestapptesttask.data.network.dto.SingleMovieResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MoviesApiService {

    @GET(MOVIES)
    suspend fun getMovies(@Header(API_HEADER) key: String = API_KEY): Response<MoviesResponseDTO>

    @GET(MOVIE)
    suspend fun getMovieById(@Path(ID) id: Int, @Header(API_HEADER) key: String = API_KEY) : Response<SingleMovieResponseDTO>
}