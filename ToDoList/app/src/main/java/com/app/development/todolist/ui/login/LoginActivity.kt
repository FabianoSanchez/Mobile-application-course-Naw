package com.app.development.todolist.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.development.todolist.R
import com.app.development.todolist.ui.home.HomeActivity
import com.app.development.todolist.util.Preference
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initGoogleSignIn()
        initViewModel()
    }

    private fun onClick() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun initView() {
        findViewById<SignInButton>(R.id.btnSignIn).setOnClickListener { onClick() }

    }

    private fun initGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val prefs = getSharedPreferences(Preference.PREFS_FILENAME, Preference.PRIVATE_MODE)
            prefs.edit().putBoolean(Preference.FIRST_TIME, false).apply()
            val account = completedTask.getResult(ApiException::class.java)
            loginViewModel.getAuthToken(account!!)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            println("Sign in Result: failed Code= ${e.statusCode}")
        }
    }

    companion object {
        const val RC_SIGN_IN = 100
    }


}
