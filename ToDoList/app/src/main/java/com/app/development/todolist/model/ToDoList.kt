package com.app.development.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ToDoList(
    @PrimaryKey var id: String,
    var todoItems  :List<ToDo>
)