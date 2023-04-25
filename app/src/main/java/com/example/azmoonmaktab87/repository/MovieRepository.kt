package com.example.azmoonmaktab87.repository

import com.example.azmoonmaktab87.api.MovieApi
import com.example.data.Movie
import com.example.data.MovieDao
import org.intellij.lang.annotations.Language
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi,private val movieDao: MovieDao) {
    fun getMovies(apiKey:String,language:String,page:Int)=movieApi.getMovies(apiKey, language, page)

    suspend fun insertMovie(movie: Movie)=movieDao.insert(movie)
    fun getMovies()=movieDao.getMovies()
}