package com.example.popularmovies.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object{
        private const val baseDiscoverUrl = "https://api.themoviedb.org/3/discover/"

        fun createApi(): MovieApiService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            val moviesApi = Retrofit.Builder()
                .baseUrl(baseDiscoverUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            return moviesApi.create(MovieApiService::class.java)
        }
    }
}