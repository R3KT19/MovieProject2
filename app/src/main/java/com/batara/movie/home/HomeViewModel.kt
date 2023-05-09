package com.batara.movie.home

import androidx.lifecycle.ViewModel
import com.batara.movie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase)  : ViewModel(){
    val movie = movieUseCase.getAllMovie()
}