package com.batara.movie.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.batara.movie.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getAllFavoriteMovie() : LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie : MovieEntity)
}