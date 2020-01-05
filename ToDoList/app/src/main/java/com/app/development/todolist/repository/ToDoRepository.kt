package com.app.development.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.app.development.todolist.database.CalendarRoomDatabase
import com.app.development.todolist.database.ToDoDao
import com.app.development.todolist.model.ToDoList


/** ToDoRepository uses [context]. Used for accessing the room database.*/
class ToDoRepository(context: Context) {


    private var toDoDao: ToDoDao

    init {
        /** Initialize the database with [context]*/
        val database = CalendarRoomDatabase.getDatabase(context)
        toDoDao = database!!.toDoDao()
    }

    /** Insert a [ToDoList] into the database.*/
    suspend fun insertTodo(toDo: ToDoList) {
        return toDoDao.insertToDo(toDo)
    }

    /** Delete all tables from ToDoList database*/
    suspend fun nukeTable() {
        return toDoDao.nukeTable()
    }

    /** Return a [ToDoList] object, based on the supplied [eventId]*/
    fun getToDo(eventId: String): LiveData<ToDoList> {
        return toDoDao.getToDo(eventId)
    }

    /** Return all [ToDoList] objects in list*/
    fun getAllTodo(): LiveData<List<ToDoList>> {
        return toDoDao.getAllToDo()
    }

}