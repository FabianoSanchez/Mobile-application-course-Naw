package com.example.rockpaperscissors.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime

    const val RECORD_HISTORY = 100
    const val draw = 0
    const val win = 1
    const val loss = 2

class MainActivity : AppCompatActivity() {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameRepository = GameRepository(this)
        getStatistics()
        initViews()
    }

    private fun initViews(){
        rockButton.setOnClickListener{onRockClick()}
        paperButton.setOnClickListener{onPaperClick()}
        scissorsButton.setOnClickListener{onScissorsClick()}
    }

    private fun onRockClick() {
        val computerResId = Game.PLAYS_RES_DRAWABLE_IDS.random()
        val computerThrow = Game.PLAYS_RES_DRAWABLE_IDS.indexOf(computerResId)
        val result: Int
        ivPlayer.setImageDrawable(getDrawable(R.drawable.rock))
        ivComputer.setImageDrawable(getDrawable(computerResId))
        when (computerThrow) {



            0 -> {
                tvCurrentResult.text = getString(R.string.draw_text)
                result = draw
            }
            1 -> {
                tvCurrentResult.text = getString(R.string.loss_text)
                result = loss
            }
            else -> {
                tvCurrentResult.text = getString(R.string.win_text)
                result = win
            }
        }
        val game = Game(OffsetDateTime.now(), computerResId, R.drawable.rock, result)
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
            getStatistics()
        }
    }

    private fun getStatistics(){
        mainScope.launch {
            val statistics = withContext(Dispatchers.IO){
                gameRepository.getStatistics()
            }
            tvStatistics.text = "Win: ${statistics.wins}   Draw: ${statistics.draws}    Lose:  ${statistics.losses}"
        }
    }

    private fun onPaperClick(){
        val computerResId = Game.PLAYS_RES_DRAWABLE_IDS.random()
        val computerThrow = Game.PLAYS_RES_DRAWABLE_IDS.indexOf(computerResId)
        val result: Int
        ivPlayer.setImageDrawable(getDrawable(R.drawable.paper))
        ivComputer.setImageDrawable(getDrawable(computerResId))
        when (computerThrow) {
            0 -> {
                tvCurrentResult.text = getString(R.string.win_text)
                result = win
            }
            1 -> {
                tvCurrentResult.text = getString(R.string.draw_text)
                result = draw
            }
            else -> {
                tvCurrentResult.text = getString(R.string.loss_text)
                result = loss
            }
        }
        val game = Game(OffsetDateTime.now(), computerResId, R.drawable.paper, result)
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
            getStatistics()

        }
    }

    private fun onScissorsClick(){
        val computerResId = Game.PLAYS_RES_DRAWABLE_IDS.random()
        val computerThrow = Game.PLAYS_RES_DRAWABLE_IDS.indexOf(computerResId)
        val result: Int
        ivPlayer.setImageDrawable(getDrawable(R.drawable.scissors))
        ivComputer.setImageDrawable(getDrawable(computerResId))
        when (computerThrow) {
            0 -> {
                tvCurrentResult.text = getString(R.string.loss_text)
                result = loss
            }
            1 -> {
                tvCurrentResult.text = getString(R.string.win_text)
                result = win
            }
            else -> {
                tvCurrentResult.text = getString(R.string.draw_text)
                result = draw
            }
        }
        val game = Game(OffsetDateTime.now(), computerResId,R.drawable.scissors, result)
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
            getStatistics()
        }
    }
    private fun openHistory(){
        val intent = Intent(this,RecordHistory::class.java)
        startActivityForResult(intent,RECORD_HISTORY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_history ->{
                openHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == RECORD_HISTORY){
            getStatistics()
        }

    }



}
