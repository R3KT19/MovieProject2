package com.batara.movie.core.domain.repository

import androidx.lifecycle.LiveData
import com.batara.movie.core.data.source.Resource
import com.batara.movie.core.domain.model.Movie

interface IMovieRepository {
    fun getAllMovie() : LiveData<Resource<List<Movie>>>
    fun getFavoriteMovie() : LiveData<List<Movie>>
    fun setFavoriteMovie(movie : Movie, state : Boolean)
}