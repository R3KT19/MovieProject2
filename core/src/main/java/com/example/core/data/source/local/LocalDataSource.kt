package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao){
    companion object{
        private var instance : LocalDataSource? = null
    }

    fun getAllMovie() : Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie() : Flow<List<MovieEntity>> = movieDao.getAllFavoriteMovie()

    suspend fun insertMovie(movieList : List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie : MovieEntity, newState : Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}