package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.model.Game

@Dao
interface GameDao{

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteRecords()

    @Query("SELECT * FROM gameTable")
    suspend fun getAllRecords(): List<Game>

    @Query("SELECT count(id) FROM gameTable WHERE result = '1'")
    suspend fun getWins():Int

    @Query("SELECT count(id) FROM gameTable WHERE result = '2'")
    suspend fun getLosses():Int

    @Query("SELECT count(id) FROM gameTable WHERE result = '0'")
    suspend fun getDraws():Int

}