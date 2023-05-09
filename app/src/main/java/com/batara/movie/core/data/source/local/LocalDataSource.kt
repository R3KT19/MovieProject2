package com.batara.movie.core.data.source.local

import androidx.lifecycle.LiveData
import com.batara.movie.core.data.source.local.entity.MovieEntity
import com.batara.movie.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){
    companion object{
        private var instance : LocalDataSource? = null

        fun getInstance(movieDao: MovieDao) : LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie() : LiveData<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie() : LiveData<List<MovieEntity>> = movieDao.getAllFavoriteMovie()

    fun insertMovie(movieList : List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie : MovieEntity, newState : Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}