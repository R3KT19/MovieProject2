package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {
    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null
    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getAllMovieError: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}