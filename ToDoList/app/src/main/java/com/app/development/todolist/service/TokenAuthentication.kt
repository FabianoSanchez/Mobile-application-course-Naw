package com.app.development.todolist.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/** Class for initializing the Retrofit with the baseUrl for receiving the tokens*/
abstract class TokenAuthentication {


    companion object {
        private const val baseUrl = "https://oauth2.googleapis.com/"

        /** Initialize the Api so that the [TokenAuthenticationService] can be used to make Api calls. Returns [TokenAuthenticationService]*/
        fun createApi(): TokenAuthenticationService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val tokenApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return tokenApi.create(TokenAuthenticationService::class.java)
        }
    }
}
