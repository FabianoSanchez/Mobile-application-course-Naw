package com.app.development.todolist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.development.todolist.model.ToDo
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

public class EventDetailViewModel(application: Application):AndroidViewModel(application){

    private val toDoRepository = ToDoRepository(application.applicationContext)

    lateinit var toDo: LiveData<ToDoList>

    fun getListOfToDo(eventId: String){
        toDo = toDoRepository.getToDo(eventId)
    }

    fun insertToDo(toDoList: ToDoList){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                toDoRepository.insertTodo(toDoList)
            }
        }
    }
}