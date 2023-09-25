package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class GetMoviesByTitleUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {

    suspend fun invoke(query: String) = moviesDbRepository.getMoviesByQuery(query)
}