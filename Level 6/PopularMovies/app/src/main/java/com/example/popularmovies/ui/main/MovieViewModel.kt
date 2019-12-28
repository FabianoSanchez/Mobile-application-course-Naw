package com.example.popularmovies.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.Results
import com.example.popularmovies.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class MovieViewModel (application: Application) : AndroidViewModel(application){

    private val movieRepository = MovieRepository()
    val movies = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()

    public fun getMoviesInYear(year:Int){
        movieRepository.getMovies(year).enqueue(object:Callback<Results>{
            override fun onFailure(call: Call<Results>, t: Throwable) {
                error.value = t.message
            }

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful){
                    movies.apply {value = response.body()?.results}
                }
                else error.value = "An error Occured: ${response.errorBody().toString()}"
            }
        })
    }
}