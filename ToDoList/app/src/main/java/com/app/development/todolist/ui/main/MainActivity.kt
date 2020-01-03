package com.app.development.todolist.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.development.todolist.ui.home.HomeActivity
import com.app.development.todolist.R
import com.app.development.todolist.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class MainActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)

        validateAccount(account)
        println("Id Token ${account?.idToken}")
    }

    private fun validateAccount(account: GoogleSignInAccount?){
        if(account == null){
             val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


}
