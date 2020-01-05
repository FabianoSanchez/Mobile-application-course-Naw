package com.app.development.todolist.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.development.todolist.R
import com.app.development.todolist.ui.home.HomeActivity
import com.app.development.todolist.ui.login.LoginActivity
import com.app.development.todolist.util.Preference
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart(){
        super.onStart()
//        val account = GoogleSignIn.getLastSignedInAccount(this)
        val prefs = this.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
        val firstTime = prefs.getBoolean(Preference.FIRST_TIME,true)
        validateAccount(firstTime)
    }

    private fun validateAccount(firstTime : Boolean){
        if(firstTime){
             val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


}
