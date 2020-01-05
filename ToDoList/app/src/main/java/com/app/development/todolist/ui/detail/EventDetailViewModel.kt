package com.app.development.todolist.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** ViewModel for EventDetailActivity */
class EventDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoRepository = ToDoRepository(application.applicationContext)

    lateinit var toDo: LiveData<ToDoList>

    /** Initializes [toDo_] with ToDoList based on the eventId */
    fun getListOfToDo(eventId: String) {
        toDo = toDoRepository.getToDo(eventId)
    }


    /** Insert a [toDoList] into the ToDoList table */
    fun insertToDo(toDoList: ToDoList) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                toDoRepository.insertTodo(toDoList)
            }
        }
    }
}