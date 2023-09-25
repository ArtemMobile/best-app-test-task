package com.example.bestapptesttask.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapptesttask.data.Resource
import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.data.network.dto.Doc
import com.example.bestapptesttask.data.network.dto.MoviesResponseDTO
import com.example.bestapptesttask.data.network.dto.SingleMovieResponseDTO
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.domain.usecase.BookMarkMovieUseCase
import com.example.bestapptesttask.domain.usecase.DeleteAllBookMarksUseCase
import com.example.bestapptesttask.domain.usecase.GetBookMarkedMoviesFromDataBaseUseCase
import com.example.bestapptesttask.domain.usecase.GetBookmarkedMoviesByTitleUseCase
import com.example.bestapptesttask.domain.usecase.GetMovieByIdUseCase
import com.example.bestapptesttask.domain.usecase.GetMovieIsBookmarkedUseCase
import com.example.bestapptesttask.domain.usecase.GetMoviesByParamUseCase
import com.example.bestapptesttask.domain.usecase.GetMoviesByTitleUseCase
import com.example.bestapptesttask.domain.usecase.GetMoviesFromDatabaseUseCase
import com.example.bestapptesttask.domain.usecase.GetMoviesFromRemoteUseCase
import com.example.bestapptesttask.domain.usecase.SaveMoviesToDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesFromRemoteUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMoviesFromDatabaseUseCase: GetMoviesFromDatabaseUseCase,
    private val saveMoviesToDatabaseUseCase: SaveMoviesToDatabaseUseCase,
    private val getBookMarkedMoviesFromDataBaseUseCase: GetBookMarkedMoviesFromDataBaseUseCase,
    private val bookMarkMovieUseCase: BookMarkMovieUseCase,
    private val deleteAllBookMarksUseCase: DeleteAllBookMarksUseCase,
    private val getMoviesByTitleUseCase: GetMoviesByTitleUseCase,
    private val getMoviesByParamUseCase: GetMoviesByParamUseCase,
    private val getMovieIsBookmarkedUseCase: GetMovieIsBookmarkedUseCase,
    private val getBookmarkedMoviesByTitleUseCase: GetBookmarkedMoviesByTitleUseCase
) : ViewModel() {

    init {
        getAllMoviesFromDatabase()
    }

    private var _movieById = MutableLiveData<Resource<Movie>>()
    val movieById: LiveData<Resource<Movie>> = _movieById

    private var _remoteMovies = MutableLiveData<Resource<MoviesResponseDTO>>()
    val remoteMovies: LiveData<Resource<MoviesResponseDTO>> = _remoteMovies

    private var _dbMovies = MutableLiveData<List<MovieDb>>()
    val dbMovies: LiveData<List<MovieDb>> = _dbMovies

    private var _bookmarkedMovies = MutableLiveData<List<MovieDb>>()
    val bookmarkedMovies: LiveData<List<MovieDb>> = _bookmarkedMovies

    private var _movieIsBookmarked = MutableLiveData<Boolean>()
    val movieIsBookmarked: LiveData<Boolean> = _movieIsBookmarked

    fun getMoviesFromRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getMoviesUseCase.invoke()
            withContext(Dispatchers.Main) {
                _remoteMovies.value = response
            }
        }
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getMovieByIdUseCase.invoke(id)
            withContext(Dispatchers.Main) {
                _movieById.value = response
            }
        }
    }

    fun saveMoviesToLocalDatabase(movies: List<Doc>) {
        viewModelScope.launch {
            saveMoviesToDatabaseUseCase.invoke(movies)
        }
    }

    fun getAllMoviesFromDatabase() {
        viewModelScope.launch {
            val movies = getMoviesFromDatabaseUseCase.invoke()
            _dbMovies.value = movies
        }
    }

    fun getBookmarkedMoviesFromDatabase() {
        viewModelScope.launch {
            val movies = getBookMarkedMoviesFromDataBaseUseCase.invoke()
            _bookmarkedMovies.value = movies
        }
    }

    fun bookMarkMovie(movieDb: MovieDb) {
        viewModelScope.launch {
            bookMarkMovieUseCase.invoke(movieDb)
        }
    }


    fun bookMarkMovie(movie: Movie, bookmarked: Boolean) {
        viewModelScope.launch {
            bookMarkMovieUseCase.invoke(movie,bookmarked)
        }
    }

    fun deleteAllBookMarks() {
        viewModelScope.launch {
            deleteAllBookMarksUseCase.invoke()
        }
    }

    fun getMoviesByTitle(query: String) {
        viewModelScope.launch {
            val movies = getMoviesByTitleUseCase.invoke(query)
            _dbMovies.value = movies
        }
    }

    fun getBookmarkedMoviesByTitle(query: String){
        viewModelScope.launch {
            val movies = getBookmarkedMoviesByTitleUseCase.invoke(query)
            _bookmarkedMovies.value = movies
        }
    }

    fun getMoviesByParam(param: String) {
        viewModelScope.launch {
            val movies = getMoviesByParamUseCase.invoke(param)
            _dbMovies.value = movies
        }
    }

    fun getMovieBookmarked(movieId: Int) {
        viewModelScope.launch {
            val bookmarked = getMovieIsBookmarkedUseCase.invoke(movieId)
            _movieIsBookmarked.value = bookmarked
        }
    }
}