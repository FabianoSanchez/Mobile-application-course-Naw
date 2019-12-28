package com.example.popularmovies.service

import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie?api_key=fee09000826e4d805b89d3056bed8807&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false")
    fun getMoviesInYear(@Query("primary_release_year")year : Int): Call<Results>
}