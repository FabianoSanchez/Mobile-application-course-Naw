package com.app.development.todolist.repository

import android.content.Context
import android.widget.Toast
import com.app.development.todolist.R
import com.app.development.todolist.service.TokenAuthentication
import com.app.development.todolist.service.TokenAuthenticationService
import com.app.development.todolist.util.Preference
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.Scope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TokenAuthenticationRepository(context: Context) {
    private val tokenAuthenticationService: TokenAuthenticationService = TokenAuthentication.createApi()

    private val context = context.applicationContext
    private val clientId = context.resources.getString(R.string.server_client_id)
    private val clientSecret = context.resources.getString(R.string.client_secret)
    private val scope = "https://www.googleapis.com/auth/calendar.readonly"
    private val grantTypeAuthorization = "authorization_code"
    private val grantTypeRefresh = "refresh_token"
    private val accessType = "offline"
    private val prompt = "consent"



    private fun silentSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(context.getString(R.string.server_client_id))
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(context,gso)
        mGoogleSignInClient.silentSignIn()
    }

     fun getAuthToken(account:GoogleSignInAccount){
            silentSignIn()
            val serverAuthCode = account.serverAuthCode
            CoroutineScope(Dispatchers.IO).launch {
                val response = tokenAuthenticationService.getAuthToken(serverAuthCode!!,
                    clientId,"",clientSecret,grantTypeAuthorization,scope,accessType,prompt)
                withContext(Dispatchers.Main){
                    try{
                        if (response.isSuccessful){
                            val prefs = context.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
                            prefs.edit().putString(Preference.REFRESH_TOKEN,response.body()?.refreshToken).apply()
                            prefs.edit().putString(Preference.ACCESS_TOKEN, response.body()?.accessToken).apply()
                        }else{
                            Toast.makeText(context,"Error: ${response.code()}",Toast.LENGTH_LONG).show()
                        }
                    }catch(e: HttpException){
                        println("Oops ${e.message()}")
                    }catch(e: Throwable){
                        println("Oops Something when wrong")
                    }
                }
            }
        }


     fun refreshToken() {
        val pref = context.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
        val refreshToken = pref.getString(Preference.REFRESH_TOKEN,"")!!
        println("refresh_token $refreshToken")
        CoroutineScope(Dispatchers.IO).launch {
            val response = tokenAuthenticationService.refreshToken(
                refreshToken, clientId, clientSecret, grantTypeRefresh)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val prefs = context.getSharedPreferences(
                            Preference.PREFS_FILENAME,
                            Preference.PRIVATE_MODE)
                        prefs.edit().remove(Preference.ACCESS_TOKEN).apply()
                        prefs.edit().putString(Preference.ACCESS_TOKEN, response.body()?.accessToken).apply()
                    } else {
                        Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (e: HttpException) {
                    println("Oops ${e.message()}")
                } catch (e: Throwable) {
                    println("Oops Something when wrong")
                }
            }
        }
    }
}

