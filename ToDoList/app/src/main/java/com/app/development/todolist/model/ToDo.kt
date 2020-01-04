package com.app.development.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ToDo(
    var toDo: String,
    var isCompleted: Boolean
)