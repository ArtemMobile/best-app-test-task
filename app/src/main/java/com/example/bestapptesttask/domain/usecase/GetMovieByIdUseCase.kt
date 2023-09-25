package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.repository.MoviesApiRepository
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val moviesApiRepository: MoviesApiRepository) {

    suspend fun invoke(id: Int) = moviesApiRepository.getMovieById(id)
}