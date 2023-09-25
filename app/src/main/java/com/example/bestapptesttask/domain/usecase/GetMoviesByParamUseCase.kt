package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class GetMoviesByParamUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend fun invoke(param: String) = moviesDbRepository.getMoviesByParam(param)
}