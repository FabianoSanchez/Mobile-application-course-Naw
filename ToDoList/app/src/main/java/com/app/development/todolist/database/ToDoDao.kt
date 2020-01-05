package com.app.development.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.development.todolist.model.ToDoList

@Dao
interface ToDoDao {

    /** Delete all tables from ToDOList*/
    @Query("DELETE FROM ToDoList")
    suspend fun nukeTable()

    /** Return a [ToDoList] object, based on the supplied [eventId]*/
    @Query("SELECT * FROM ToDoList WHERE id = :eventId")
    fun getToDo(eventId: String): LiveData<ToDoList>


    /** Return all [ToDoList] objects in list*/
    @Query("SELECT * FROM ToDoList")
    fun getAllToDo(): LiveData<List<ToDoList>>


    /**
     * Insert a [ToDoList] object.
     * If eventId already exists, delete the one in database and insert new [ToDoList] object
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDoList)

}