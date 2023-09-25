package com.example.bestapptesttask.domain.repository

import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.data.network.dto.Doc
import com.example.bestapptesttask.domain.model.Movie

interface MoviesDbRepository {

    suspend fun saveMovies(movies: List<Doc>)

    suspend fun getMovies(): List<MovieDb>

    suspend fun getBookmarkedMovies(): List<MovieDb>

    suspend fun bookMarkMovie(movie: MovieDb)

    suspend fun bookMarkMovie(movie: Movie, bookmarked: Boolean)

    suspend fun deleteAllBookmarks()

    suspend fun getMoviesByQuery(query: String): List<MovieDb>

    suspend fun getBookmarkedMoviesByQuery(query: String): List<MovieDb>

    suspend fun getMoviesByParam(param: String): List<MovieDb>

    suspend fun getMovieIsBookmarked(idMovie: Int): Boolean
}