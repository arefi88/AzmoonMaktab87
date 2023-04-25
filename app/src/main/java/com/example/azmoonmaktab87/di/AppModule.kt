package com.example.azmoonmaktab87.di

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.azmoonmaktab87.api.MovieApi
import com.example.azmoonmaktab87.database.MovieDatabase
import com.example.data.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit):MovieApi = retrofit.create(MovieApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,MovieDatabase::class.java,
        "movie_database"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:MovieDatabase)=db.getDao()


    @Provides
    @Singleton
    fun provideMovie()=Movie()
}