package com.example.notepad.ui.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.notepad.R
import com.example.notepad.ui.edit.EditActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        initViews()
        initViewModels()

    }

    private fun initViews(){
        fab.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditActivity.EXTRA_NOTE, mainViewModel.note.value)
            startActivity(intent)
        }
    }

    private fun initViewModels(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

            mainViewModel.note.observe(this, Observer { note ->
                if(note != null){
                    tvTitle.text = note.title
                    tvNote.text = note.text
                    tvUpdated.text = getString(R.string.last_updated,note.lastUpdated.toString())
                }
            })
    }


}
