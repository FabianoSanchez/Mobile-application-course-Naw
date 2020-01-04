package com.app.development.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.app.development.todolist.database.CalendarRoomDatabase
import com.app.development.todolist.database.ToDoDao
import com.app.development.todolist.model.ToDoList

class ToDoRepository (context: Context){


    private var toDoDao: ToDoDao

    init{
        val database = CalendarRoomDatabase.getDatabase(context)
        toDoDao = database!!.toDoDao()
    }



    suspend fun insertTodo(toDo:ToDoList){
        return toDoDao.insertToDo(toDo)
    }

    suspend fun nukeTable(){
        return toDoDao.nukeTable()
    }

    fun getToDo(eventId:String):LiveData<ToDoList>{
        return toDoDao.getToDo(eventId)
    }

     fun getAllTodo():LiveData<List<ToDoList>>{
        return toDoDao.getAllToDo()
    }

}