package com.example.bestapptesttask.di

import android.content.Context
import androidx.room.Room
import com.example.bestapptesttask.data.database.MoviesDao
import com.example.bestapptesttask.data.database.MoviesDatabase
import com.example.bestapptesttask.data.mapper.MovieMapper
import com.example.bestapptesttask.data.network.service.MoviesApiService
import com.example.bestapptesttask.data.repository.MoviesApiRepositoryImpl
import com.example.bestapptesttask.data.repository.MoviesDbRepositoryImpl
import com.example.bestapptesttask.domain.repository.MoviesApiRepository
import com.example.bestapptesttask.domain.repository.MoviesDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // Database
    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MoviesDatabase.DATABASE_NAME
        ).build()
    }

    // Repository
    @Provides
    @Singleton
    fun provideMoviesApiRepository(
        moviesApiService: MoviesApiService,
        movieMapper: MovieMapper
    ): MoviesApiRepository {
        return MoviesApiRepositoryImpl(moviesApiService, movieMapper)
    }

    @Provides
    @Singleton
    fun provideMoviesDbRepository(
        moviesDatabase: MoviesDatabase,
        movieMapper: MovieMapper
    ): MoviesDbRepository {
        return MoviesDbRepositoryImpl(moviesDatabase, movieMapper)
    }
}