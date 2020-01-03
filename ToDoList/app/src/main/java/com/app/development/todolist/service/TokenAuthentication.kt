package com.app.development.todolist.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class TokenAuthentication{


    companion object{
        private const val baseUrl = "https://oauth2.googleapis.com/"

        fun createApi(): TokenAuthenticationService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            val tokenApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return tokenApi.create(TokenAuthenticationService::class.java)
        }
    }
}
