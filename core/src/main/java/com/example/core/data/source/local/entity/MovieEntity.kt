package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    var movieId : Int,

    @ColumnInfo(name = "pictureUrl")
    var pictureUrl : String,

    @ColumnInfo(name = "backdropUrl")
    var backdropUrl : String,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "description")
    var description : String,

    @ColumnInfo(name = "popularity")
    var popularity : String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate : String,

    @ColumnInfo(name = "vote")
    var vote : String,

    @ColumnInfo(name = "voteCount")
    var voteCount : String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false
)
