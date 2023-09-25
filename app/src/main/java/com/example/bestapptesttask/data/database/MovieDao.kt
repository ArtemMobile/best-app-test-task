package com.example.bestapptesttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.model.MovieDb

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieDb>

    @Query("SELECT * FROM movies WHERE bookmarked = 1")
    suspend fun getBookmarkedMovies(): List<MovieDb>

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    suspend fun getMoviesByTitle(query: String): List<MovieDb>

    @Query("SELECT * FROM movies WHERE title LIKE :query AND bookmarked = 1")
    suspend fun getBookmarkedMoviesByTitle(query: String): List<MovieDb>

    @Query("SELECT * FROM movies ORDER BY title ASC")
    suspend fun getMoviesByAlphabet(): List<MovieDb>

    @Query("SELECT * FROM movies ORDER BY rating DESC")
    suspend fun getMoviesByRating(): List<MovieDb>

    @Query("SELECT * FROM movies ORDER BY votes DESC")
    suspend fun getMoviesByVotes(): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieToDb(movie: MovieDb)

    @Update
    suspend fun updateFilmBookmark(movie: MovieDb)

    @Query("UPDATE movies SET bookmarked = 0")
    suspend fun deleteAllBookmarks()

    @Query("SELECT bookmarked FROM movies WHERE id=:movieId")
    suspend fun getMovieBookmarked(movieId: Int): Boolean
}