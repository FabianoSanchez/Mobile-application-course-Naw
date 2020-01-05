package com.app.development.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.development.todolist.model.ToDoList

@Dao
interface ToDoDao {

    @Query("DELETE FROM ToDoList")
    suspend fun nukeTable()

    @Query("SELECT * FROM ToDoList WHERE id = :eventId")
    fun getToDo(eventId: String): LiveData<ToDoList>

    @Query("SELECT * FROM ToDoList")
    fun getAllToDo(): LiveData<List<ToDoList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDoList)

}