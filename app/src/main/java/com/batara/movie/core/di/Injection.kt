package com.batara.movie.core.di

import android.content.Context
import com.batara.movie.core.data.source.MovieRepository
import com.batara.movie.core.data.source.local.LocalDataSource
import com.batara.movie.core.data.source.local.room.MovieDatabase
import com.batara.movie.core.data.source.remote.RemoteDataSource
import com.batara.movie.core.data.source.remote.network.ApiConfig
import com.batara.movie.core.domain.repository.IMovieRepository
import com.batara.movie.core.domain.usecase.MovieInteractor
import com.batara.movie.core.domain.usecase.MovieUseCase
import com.batara.movie.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context) : IMovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context) : MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}