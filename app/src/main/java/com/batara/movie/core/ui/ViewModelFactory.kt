package com.batara.movie.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batara.movie.core.di.Injection
import com.batara.movie.core.domain.usecase.MovieUseCase

class ViewModelFactory private constructor(private val movieUseCase : MovieUseCase)
    : ViewModelProvider.NewInstanceFactory(){

        companion object{
            @Volatile
            private var instance : ViewModelFactory? = null

            fun getInstance(context: Context) : ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(
                        Injection.provideMovieUseCase(context)
                    )
                }
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>): T =
        }
}