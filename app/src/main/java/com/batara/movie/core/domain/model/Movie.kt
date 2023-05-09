package com.batara.movie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val movieId: Int,
    val pictureUrl : String,
    val backdropUrl : String,
    val title : String,
    val description : String,
    val popularity : String,
    val releaseDate : String,
    val vote : String,
    val voteCount : String,
    val isFavorite : Boolean
) : Parcelable