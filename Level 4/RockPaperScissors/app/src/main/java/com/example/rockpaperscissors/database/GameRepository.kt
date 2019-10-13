package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.model.Statistics


class GameRepository(context: Context){

    private val gameDao: GameDao

    init{
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllRecords():List<Game>{
        return gameDao.getAllRecords()
    }

    suspend fun getStatistics(): Statistics{
        val wins = gameDao.getWins()
        val losses = gameDao.getLosses()
        val draws = gameDao.getDraws()

        return Statistics(wins,losses,draws)
    }

    suspend fun insertGame(game:Game){
        return gameDao.insertGame(game)
    }


    suspend fun deleteRecords(){
        return gameDao.deleteRecords()
    }

}