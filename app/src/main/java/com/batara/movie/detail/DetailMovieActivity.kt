package com.batara.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.batara.movie.R
import com.batara.movie.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailMovieBinding
    private lateinit var detailMovieViewModel: DetailMovieViewModel
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}