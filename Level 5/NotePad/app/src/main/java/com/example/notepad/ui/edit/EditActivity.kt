package com.example.notepad.ui.edit

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.notepad.R
import com.example.notepad.model.Note

import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.content_edit.*
import java.util.*



class EditActivity : AppCompatActivity() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Edit Notepad"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }


    private fun initViewModel(){
        editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        editViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editViewModel.note.observe(this, Observer{ note ->
            if(note != null){
                etTitle.setText(note.title)
                etNote.setText(note.text)
            }
        })

        editViewModel.error.observe(this, Observer {error ->
            Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
        })

        editViewModel.success.observe(this, Observer {success ->
            if(success) finish()
        })
    }

    private fun initViews(){
        fab.setOnClickListener {
            editViewModel.note.value?.apply {
                title = etTitle.text.toString()
                lastUpdated = Date()
                text = etNote.text.toString()
            }
            editViewModel.updateNote()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }


}


