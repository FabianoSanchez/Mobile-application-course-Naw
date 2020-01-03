package com.app.development.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.development.todolist.model.GoogleCalendar
import com.app.development.todolist.model.Token

@Dao
interface TokenDao{

    @Query("SELECT * FROM Token LIMIT 1")
    fun getToken(): LiveData<Token>

    @Insert
    suspend fun insertToken(token:Token)

    @Delete
    suspend fun deleteToken(token:Token)

    @Update
    suspend fun updateToken(token:Token)
}