package com.example.azmoonmaktab87.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.Movie
import com.example.data.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun getDao():MovieDao
}