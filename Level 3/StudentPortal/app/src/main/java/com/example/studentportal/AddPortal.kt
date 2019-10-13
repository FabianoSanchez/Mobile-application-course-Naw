package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*




class AddPortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Create a Portal"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)
        initViews()
    }


    private fun initViews(){
        addPortalButton.setOnClickListener{ onAddPortal()}
    }

    private fun onAddPortal(){
        if(tiTitle.text.toString().isNotBlank() && tiUrl.text.toString().isNotBlank()) {
            val portal = Portal(
                tiTitle.text.toString(),
                tiUrl.text.toString()
            )
            val resultIntent = Intent().putExtra(EXTRA_PORTAL,portal)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }else{
            Toast.makeText(this,"The Title and Url cannot be empty"
                , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    companion object{
        const val EXTRA_PORTAL = "EXTRA_PORTAL"
    }
}
