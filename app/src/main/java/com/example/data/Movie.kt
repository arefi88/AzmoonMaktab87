package com.example.data

import android.graphics.Bitmap
import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title: String="",
    var posterPath:String=""
)
