package com.batara.movie.detail

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.batara.movie.BuildConfig
import com.batara.movie.R
import com.example.core.domain.model.Movie
import com.batara.movie.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, Movie::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            Glide.with(this@DetailMovieActivity)
                .load(BuildConfig.LOAD_PIC+detailMovie.backdropUrl)
                .into(binding.backdropIv)

            binding.releaseDateTv.text= detailMovie.releaseDate
            binding.voteAverageTv.text = detailMovie.vote
            binding.voteCountTv.text = detailMovie.voteCount
            binding.popularityTv.text = detailMovie.popularity
            binding.descTv.text = detailMovie.description

            var statusFavorite = detailMovie.isFavorite
            setFavorite(statusFavorite)
            binding.favoriteFab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setFavorite(statusFavorite)
            }
        }
    }

    private fun setFavorite(statusFavorite : Boolean){
        if (statusFavorite) {
            binding.favoriteFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.favoriteFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }
}