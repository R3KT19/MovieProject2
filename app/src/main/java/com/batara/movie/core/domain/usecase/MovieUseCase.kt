package com.batara.movie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.batara.movie.core.data.source.Resource
import com.batara.movie.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
    fun getFavoriteMovie(): LiveData<List<Movie>>
    fun setFavoriteMovie(movie : Movie, state: Boolean)
}