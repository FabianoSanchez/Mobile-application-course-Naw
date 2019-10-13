package com.example.rockpaperscissors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.activity_record_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordHistory : AppCompatActivity() {

    private val records = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(records)
    private lateinit var gameRepository : GameRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameRepository = GameRepository(this)
        initViews()
    }


    private fun initViews(){
        rvRecord.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvRecord.adapter = gameAdapter
        rvRecord.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        getRecordsFromDatabase()

    }

    private fun getRecordsFromDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO){
                gameRepository.getAllRecords()
            }
            this@RecordHistory.records.clear()
            this@RecordHistory.records.addAll(games)
            this@RecordHistory.gameAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteRecords(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                gameRepository.deleteRecords()
            }
            this@RecordHistory.records.clear()
            this@RecordHistory.gameAdapter.notifyDataSetChanged()
        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.record_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteRecords()
                true
            }
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
