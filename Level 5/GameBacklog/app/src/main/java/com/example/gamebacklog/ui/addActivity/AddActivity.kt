package com.example.gamebacklog.ui.addActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.mainActivity.MainActivity
import com.example.gamebacklog.ui.mainActivity.MainViewModel

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Integer.parseInt
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

public class AddActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        initViews()
    }

    private fun initViews(){
        fab.setOnClickListener {
           if(!checkInvalidGame()) insertGame()
           else{
            Toast.makeText(this,"All Fields must be filled in",Toast.LENGTH_SHORT).show()
           }
        }
    }


    private fun insertGame(){
        val year = etYear.text.toString()
        val month = etMonth.text.toString()
        val day = etDay.text.toString()
        val sdf = SimpleDateFormat("yyyy-mm-dd",Locale.ENGLISH)
        val date = sdf.parse("$year-$month-$day")
        val game = Game(etTitle.text.toString(),date,etPlatform.text.toString())

        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_GAME,game)
        setResult(Activity.RESULT_OK,resultIntent)
        finish()
    }

    private fun checkInvalidGame(): Boolean{
        return etYear.text.isNullOrEmpty() || etMonth.text.isNullOrEmpty() || etDay.text.isNullOrEmpty() || etTitle.text.isNullOrEmpty() || etPlatform.text.isNullOrEmpty()
    }

    companion object{
        const val EXTRA_GAME ="EXTRA_GAME"
    }

}
