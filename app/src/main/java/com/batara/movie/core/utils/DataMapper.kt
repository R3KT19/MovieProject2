package com.batara.movie.core.utils

import com.batara.movie.core.data.source.local.entity.MovieEntity
import com.batara.movie.core.data.source.remote.response.MovieResponse
import com.batara.movie.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                movieId = it.id,
                pictureUrl = it.posterPath,
                backdropUrl = it.backdropPath,
                title = it.originalTitle,
                description = it.overview,
                popularity = it.popularity,
                releaseDate = it.releaseDate,
                vote = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                pictureUrl = it.pictureUrl,
                backdropUrl = it.backdropUrl,
                title = it.title,
                description = it.description,
                popularity = it.popularity,
                releaseDate = it.releaseDate,
                vote = it.vote,
                voteCount = it.voteCount,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        pictureUrl = input.pictureUrl,
        backdropUrl = input.backdropUrl,
        title = input.title,
        description = input.description,
        popularity = input.popularity,
        releaseDate = input.releaseDate,
        vote = input.vote,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )
}