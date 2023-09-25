package com.example.bestapptesttask.data.mapper

import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.data.network.dto.Doc
import com.example.bestapptesttask.data.network.dto.PersonDTO
import com.example.bestapptesttask.data.network.dto.SingleMovieResponseDTO
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.model.Person
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapDtoModelToDbModel(movieDTO: Doc): MovieDb {
        return MovieDb(
            id = movieDTO.id,
            rating = movieDTO.rating.kp,
            image = movieDTO.poster.url,
            title = movieDTO.name,
            bookmarked = false,
            votes = movieDTO.votes.kp
        )
    }

    fun mapDtoModelToEntity(singleMovieResponseDTO: SingleMovieResponseDTO): Movie {
        return Movie(
            id = singleMovieResponseDTO.id,
            image = singleMovieResponseDTO.poster.previewUrl,
            title = singleMovieResponseDTO.name,
            plot = singleMovieResponseDTO.description,
            rating = singleMovieResponseDTO.rating.kp,
            releaseDate = singleMovieResponseDTO.year,
            actorList = mapDtoPersonToEntity(singleMovieResponseDTO.personDTOS),
            votes = singleMovieResponseDTO.votes.kp,
            trailer = if (singleMovieResponseDTO.videos.trailers.isNotEmpty()) singleMovieResponseDTO.videos.trailers[0].url else null
        )
    }

    fun mapEntityToDbModel(movie: Movie, bookmarked: Boolean): MovieDb {
        return MovieDb(
            id = movie.id,
            rating = movie.rating,
            image = movie.image,
            title = movie.title,
            bookmarked = bookmarked,
            votes = movie.votes
        )
    }

    private fun mapDtoPersonToEntity(personDTOs: List<PersonDTO>): List<Person> {
        return personDTOs.filter { it.enProfession == "actor" }.map {
            Person(
                name = it.name,
                asCharacter = it.description,
                image = it.photo
            )
        }
    }

}