package com.app.development.todolist.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.development.todolist.R
import com.app.development.todolist.ui.main.MainActivity
import com.app.development.todolist.util.Preference
import com.app.development.todolist.util.Util
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {


    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var progressOverlay: View
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var prefs: SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressOverlay = findViewById(R.id.progress_overlay)
        progressOverlay.bringToFront()
        prefs = this.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)

        initNavigation()
        initGoogleSignIn()
        initViewModel()

    }


    private fun initGoogleSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    private fun initViewModel(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private fun initNavigation(){
        val navController = findNavController(R.id.navHomeFragment)
        NavigationUI.setupWithNavController(navView, navController)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.toDoFragment,R.id.homeFragment
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
        deletePreferencesAndRoom()

        Util.animateView(progressOverlay,View.VISIBLE,0.4f,200)
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,{
            mGoogleSignInClient.signOut()
            val intent = Intent(this,MainActivity::class.java)
            Util.animateView(progressOverlay,View.GONE,0f,200)
            startActivity(intent)
        })

    }
    private fun deletePreferencesAndRoom(){
        prefs.edit().clear().apply()
        homeViewModel.deleteAllTables()
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
