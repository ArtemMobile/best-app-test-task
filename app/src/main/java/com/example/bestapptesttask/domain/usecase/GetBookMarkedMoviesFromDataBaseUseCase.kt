package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class GetBookMarkedMoviesFromDataBaseUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {

    suspend fun invoke() = moviesDbRepository.getBookmarkedMovies()
}