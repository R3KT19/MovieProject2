package com.batara.movie.detail

import androidx.lifecycle.ViewModel
import com.batara.movie.core.domain.model.Movie
import com.batara.movie.core.domain.usecase.MovieUseCase

class DetailMovieViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie : Movie, newStatus : Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}