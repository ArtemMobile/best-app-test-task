package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class GetMovieIsBookmarkedUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {

    suspend fun invoke(movieId: Int) = moviesDbRepository.getMovieIsBookmarked(movieId)
}