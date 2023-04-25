package com.example.azmoonmaktab87

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azmoonmaktab87.repository.MovieRepository
import com.example.data.Movie
import com.example.data.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel() {
    private val _moviesLiveData = MutableLiveData<Movies>()
    val moviesLiveData: LiveData<Movies> = _moviesLiveData

    private val _movieListDatabase = MutableLiveData<List<Movie>>()
    val movieListDatabase: LiveData<List<Movie>> = _movieListDatabase

    fun getMovies()=viewModelScope.launch {
        movieRepository.getMovies("a447989f2b34e1193b1194c6265c3d3f","en-Us",1).collect{
            _moviesLiveData.postValue(it)
        }
    }

    fun insertMovie(movie: Movie)=viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    fun getMoviesDatabase()=viewModelScope.launch {
        movieRepository.getMovies().collect{
            _movieListDatabase.value=it
        }
    }

}