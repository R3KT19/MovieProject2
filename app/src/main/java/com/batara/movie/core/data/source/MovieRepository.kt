package com.batara.movie.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.batara.movie.core.data.source.local.LocalDataSource
import com.batara.movie.core.data.source.remote.RemoteDataSource
import com.batara.movie.core.data.source.remote.network.ApiResponse
import com.batara.movie.core.data.source.remote.response.MovieResponse
import com.batara.movie.core.domain.model.Movie
import com.batara.movie.core.domain.repository.IMovieRepository
import com.batara.movie.core.utils.AppExecutors
import com.batara.movie.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource : RemoteDataSource,
    private val localDataSource : LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object{
        @Volatile
        private var instance : MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ) : MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllMovie()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteMovie()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute() {localDataSource.setFavoriteMovie(movieEntity, state)}
    }
}