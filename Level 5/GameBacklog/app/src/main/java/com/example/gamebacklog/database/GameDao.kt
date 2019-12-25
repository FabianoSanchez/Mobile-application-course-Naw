package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM Game")
    fun getGames() : LiveData<List<Game>>

    @Delete
    suspend fun deleteGame(game:Game)

    @Query("DELETE FROM Game")
    suspend fun deleteAllGames()

    @Insert
    suspend fun insertGame(game:Game?)

}