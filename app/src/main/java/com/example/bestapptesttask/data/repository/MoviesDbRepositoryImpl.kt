package com.example.bestapptesttask.data.repository

import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.data.database.MoviesDatabase
import com.example.bestapptesttask.data.mapper.MovieMapper
import com.example.bestapptesttask.data.network.dto.Doc
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import com.example.bestapptesttask.utils.Constants.ALPHABET
import com.example.bestapptesttask.utils.Constants.RATING
import javax.inject.Inject

class MoviesDbRepositoryImpl @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val movieMapper: MovieMapper
) :
    MoviesDbRepository {
    override suspend fun saveMovies(movies: List<Doc>) {
        movies.forEach { movieDto ->
            moviesDatabase.moviesDao.saveMovieToDb(
                movieMapper.mapDtoModelToDbModel(
                    movieDto
                )
            )
        }
    }

    override suspend fun getMovies(): List<MovieDb> {
        return moviesDatabase.moviesDao.getMovies()
    }

    override suspend fun getBookmarkedMovies(): List<MovieDb> {
        return moviesDatabase.moviesDao.getBookmarkedMovies()
    }

    override suspend fun bookMarkMovie(movie: MovieDb) {
        moviesDatabase.moviesDao.updateFilmBookmark(movie)
    }

    override suspend fun bookMarkMovie(movie: Movie, bookmarked: Boolean) {
        moviesDatabase.moviesDao.updateFilmBookmark(movieMapper.mapEntityToDbModel(movie, bookmarked))
    }

    override suspend fun deleteAllBookmarks() {
        moviesDatabase.moviesDao.deleteAllBookmarks()
    }

    override suspend fun getMoviesByQuery(query: String): List<MovieDb> {
        return moviesDatabase.moviesDao.getMoviesByTitle(query)
    }

    override suspend fun getBookmarkedMoviesByQuery(query: String): List<MovieDb> {
        return moviesDatabase.moviesDao.getBookmarkedMoviesByTitle(query)
    }

    override suspend fun getMoviesByParam(param: String): List<MovieDb> {
        return when (param) {
            ALPHABET -> {
                moviesDatabase.moviesDao.getMoviesByAlphabet()
            }
            RATING -> {
                moviesDatabase.moviesDao.getMoviesByRating()
            }
            else -> {
                moviesDatabase.moviesDao.getMoviesByVotes()
            }
        }
    }

    override suspend fun getMovieIsBookmarked(idMovie: Int): Boolean {
        return moviesDatabase.moviesDao.getMovieBookmarked(idMovie)
    }
}