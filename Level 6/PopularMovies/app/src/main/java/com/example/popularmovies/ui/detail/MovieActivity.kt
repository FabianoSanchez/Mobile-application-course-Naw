package com.example.popularmovies.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.movie_view.*

class MovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initViews()
    }

    private fun initViews(){
        val movie = intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)
        tvDescription.text = movie?.overview
        tvName.text = movie?.title
        tvRating.text = movie?.rating.toString()
        tvRelease.text = getString(R.string.release_date,movie?.release)
        tvDescription.movementMethod = ScrollingMovementMethod()
        Glide.with(this).load(movie?.getPosterUrl()).into(ivPoster)
        Glide.with(this).load(movie?.getBackgroundUrl()).into(ivMoviePicture)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    companion object{
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}
