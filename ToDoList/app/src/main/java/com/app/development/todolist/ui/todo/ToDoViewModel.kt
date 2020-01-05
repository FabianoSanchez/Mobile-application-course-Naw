package com.app.development.todolist.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.development.todolist.repository.ToDoRepository

class ToDoViewModel (application: Application):AndroidViewModel(application){

    private var todoRepository = ToDoRepository(application.applicationContext)

     var toDos = todoRepository.getAllTodo()

}