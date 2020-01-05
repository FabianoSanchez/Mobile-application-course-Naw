package com.app.development.todolist.service

import com.app.development.todolist.model.Token
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenAuthenticationService {


    /***
     * A POST request to receive a [Token] object from Google. Returns a [Response] object of [Token]
     * @param code          the [authId] from the logged in User.
     * @param clientId      the [clientId] the generated id from Google. This is a string from @Strings
     * @param redirectUri   an empty string, but is still needed in the POST url by Google
     * @param clientSecret  an generated String from Google. This is a string from @Strings
     * @param grantType     an hardcoded string: 'authorization_code'
     * @param scope         an hardcoded string, which allows the token to be used for getting Calendar data
     * @return Response<Token>
     */
    @POST("token")
    suspend fun getAuthToken(
        @Query("code") code: String,
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String,
        @Query("scope") scope: String
    ): Response<Token>


    /***
     * A POST request to refresh the [accessToken]. Returns a Response with [Token]
     * @param refreshToken  a token which allows the refreshing of the accessToken without asking for Access
     * @param clientId      The [clientId] the generated id from Google. This is a string from @Strings
     * @param clientSecret  an generated String from Google. This is a string from @Strings
     * @param grantType     an hardcoded string: 'refresh_token'.
     * @return Response<Token>
     */
    @POST("token")
    suspend fun refreshToken(
        @Query("refresh_token") refreshToken: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String
    ): Response<Token>
}