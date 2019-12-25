package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game

class GameRepository (context: Context){

    private var gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGames(): LiveData<List<Game>>{
        return gameDao.getGames()
    }

    suspend fun deleteGame(game:Game){
        return gameDao.deleteGame(game)
    }

    suspend fun insertGame(game:Game?){
        return gameDao.insertGame(game)
    }

    suspend fun deleteAllGames(){
        return gameDao.deleteAllGames()
    }


}