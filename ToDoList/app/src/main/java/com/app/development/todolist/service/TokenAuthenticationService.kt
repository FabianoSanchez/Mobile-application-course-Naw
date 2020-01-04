package com.app.development.todolist.service

import com.app.development.todolist.model.Token
import com.google.api.client.json.Json
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TokenAuthenticationService {


    @POST("token")
    suspend fun getAuthToken(@Query("code")code:String,
                     @Query("client_id") clientId :String,
                     @Query("redirect_uri")redirectUri:String,
                     @Query("client_secret")clientSecret:String,
                     @Query("grant_type") grantType: String,
                     @Query("scope")scope:String,
                     @Query("access_type")accessType:String,
                     @Query("prompt")prompt:String) : Response<Token>


    @POST("token")
    suspend fun refreshToken(@Query("refresh_token") refreshToken:String,
                     @Query("client_id") clientId:String,
                     @Query("client_secret") clientSecret:String,
                     @Query("grant_type")grantType: String): Response<Token>
}