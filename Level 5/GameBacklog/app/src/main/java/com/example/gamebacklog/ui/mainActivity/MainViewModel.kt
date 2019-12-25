package com.example.gamebacklog.ui.mainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel (application: Application) : AndroidViewModel(application){

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)



    val games: LiveData<List<Game>> = gameRepository.getGames()

    fun deleteGame(game:Game){
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun insertGame(game:Game?){
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }


}

