package com.batara.movie.core.data.source.remote.network

import com.batara.movie.BuildConfig
import com.batara.movie.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("3/discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=true&page=1&with_watch_monetization_types=flatrate")
    fun getList(): Call<ListMovieResponse>
}