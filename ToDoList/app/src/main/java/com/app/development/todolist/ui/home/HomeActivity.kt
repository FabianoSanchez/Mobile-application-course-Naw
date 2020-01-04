package com.app.development.todolist.ui.home

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewFragment
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.development.todolist.R
import com.app.development.todolist.model.GoogleCalendar
import com.app.development.todolist.ui.add.AddCalendar
import com.app.development.todolist.ui.main.MainActivity
import com.app.development.todolist.util.Preference
import com.app.development.todolist.util.Util
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.OnCompleteListener

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {


    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var progressOverlay: View
    private var prefs = Preference(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressOverlay = findViewById(R.id.progress_overlay)
        progressOverlay.bringToFront()
        initNavigation()
        initGoogleSignIn()

    }


    private fun initGoogleSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    private fun initNavigation(){
        val navController = findNavController(R.id.navHomeFragment)
        NavigationUI.setupWithNavController(navView, navController)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.toDoFragment,R.id.settingsFragment,R.id.homeFragment
        ))
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout -> {
              logOutUser()
            }
            R.id.action_calendar ->{
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOutUser(){
        deletePreferences()

        Util.animateView(progressOverlay,View.VISIBLE,0.4f,200)
        mGoogleSignInClient.signOut().addOnCompleteListener(this, {
            mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,{
                val intent = Intent(this,MainActivity::class.java)
                Util.animateView(progressOverlay,View.GONE,0f,200)
                startActivity(intent)

            })
        })
    }
    private fun deletePreferences(){
        prefs.getPreference().edit().remove(Preference.ACCESS_TOKEN).apply()
        prefs.getPreference().edit().remove(Preference.USER_AUTH_ID).apply()
        prefs.getPreference().edit().remove(Preference.CALENDAR_ID).apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    companion object{
        const val ADD_CALENDAR_REQUEST_CODE = 100
    }


}
