package com.example.popularmovies.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.Results
import com.example.popularmovies.ui.detail.MovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies,{movie ->  onMovieClick(movie)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMain)
        initViews()
        initViewModel()
    }

    private fun initViews(){
        btnSumbit.setOnClickListener{onSubmit()}

        rvMovies.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL,false)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModel(){
      viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

      viewModel.movies.observe(this, Observer {
          this@MainActivity.movies.clear()
          this@MainActivity.movies.addAll(it)
          movieAdapter.notifyDataSetChanged()
      })

      viewModel.error.observe(this, Observer {
          Snackbar.make(rvMovies,"An error occured: $it", Snackbar.LENGTH_LONG).show()
      })
    }

    private fun onSubmit(){
        if(etYear.text.isNullOrEmpty()){
            Toast.makeText(this,"Please fill in a year",Toast.LENGTH_SHORT).show()
        }
        else viewModel.getMoviesInYear(etYear.text.toString().toInt())
    }

    private fun onMovieClick(movie:Movie){
        val intent = Intent( this,MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }


}
