package com.example.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissors.model.Game

@Database(entities = [Game::class],version = 1,exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase(){

    abstract fun gameDao():GameDao

    companion object{
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameRoomDatabase: GameRoomDatabase? = null

        fun getDatabase(context: Context) : GameRoomDatabase?{
            if(gameRoomDatabase == null){
                synchronized(GameRoomDatabase::class.java){
                    if(gameRoomDatabase == null){
                        gameRoomDatabase =
                            Room.databaseBuilder(context.applicationContext, GameRoomDatabase::class.java,
                                DATABASE_NAME).build()
                    }
                }
            }
            return gameRoomDatabase
        }
    }

}