package com.example.azmoonmaktab87.api

import com.example.data.Movies
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
  @GET("3/movie/popular")
  fun getMovies(@Query("api_key") apiKey:String,
  @Query("language") language:String,
  @Query("page") page:Int):Flow<Movies>

}