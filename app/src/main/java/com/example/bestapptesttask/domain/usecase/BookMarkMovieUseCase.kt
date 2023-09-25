package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class BookMarkMovieUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {

    suspend fun invoke(movie: MovieDb) = moviesDbRepository.bookMarkMovie(movie)

    suspend fun invoke(movie: Movie, bookMarked: Boolean) = moviesDbRepository.bookMarkMovie(movie, bookMarked)
}