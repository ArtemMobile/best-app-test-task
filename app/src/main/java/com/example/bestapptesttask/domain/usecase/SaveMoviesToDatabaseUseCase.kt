package com.example.bestapptesttask.domain.usecase

import com.example.bestapptesttask.data.network.dto.Doc
import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import javax.inject.Inject

class SaveMoviesToDatabaseUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository){

    suspend fun invoke(movies: List<Doc>) = moviesDbRepository.saveMovies(movies)
}