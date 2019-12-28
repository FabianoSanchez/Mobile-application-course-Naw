package com.example.popularmovies.repository

import com.example.popularmovies.service.MovieApi
import com.example.popularmovies.service.MovieApiService

class MovieRepository{
    private val moviesApi: MovieApiService = MovieApi.createApi()


    fun getMovies(year:Int) = moviesApi.getMoviesInYear(year)
}