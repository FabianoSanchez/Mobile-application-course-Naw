package com.app.development.todolist.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.development.todolist.model.GoogleCalendar
import com.app.development.todolist.repository.TokenAuthenticationsRepository
import com.app.development.todolist.ui.home.HomeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

public class LoginViewModel(application: Application): AndroidViewModel(application){

    private var viewModel = HomeViewModel(application)
    private var tokenAuthenticationService = TokenAuthenticationsRepository(application)

    public fun getAuthToken(account:GoogleSignInAccount){
        tokenAuthenticationService.getAuthToken(account)
    }

}