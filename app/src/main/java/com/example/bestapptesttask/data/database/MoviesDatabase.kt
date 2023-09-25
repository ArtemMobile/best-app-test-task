package com.example.bestapptesttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bestapptesttask.domain.model.MovieDb

@Database(entities = [MovieDb::class], version = 3, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

    companion object {
        const val DATABASE_NAME = "movies_db"
    }
}