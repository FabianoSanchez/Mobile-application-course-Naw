package com.app.development.todolist.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.development.todolist.R
import com.app.development.todolist.ui.home.HomeActivity
import com.app.development.todolist.ui.login.LoginActivity
import com.app.development.todolist.util.Preference
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val prefs = this.getSharedPreferences(Preference.PREFS_FILENAME, Preference.PRIVATE_MODE)
        val firstTime = prefs.getBoolean(Preference.FIRST_TIME, true)
        validateAccount(firstTime, account)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun validateAccount(firstTime: Boolean, account: GoogleSignInAccount?) {
        if (firstTime) {
            revokeAndLogoutAccount()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            viewModel.getAuthToken(account!!)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun revokeAndLogoutAccount() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this, {
            mGoogleSignInClient.signOut()
        })
    }
}
