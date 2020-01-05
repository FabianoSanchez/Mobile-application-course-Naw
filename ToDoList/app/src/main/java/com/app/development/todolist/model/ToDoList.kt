package com.app.development.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Dataclass for [ToDoList]. Uses a list of [ToDoItem]. */
@Entity
data class ToDoList(
    @PrimaryKey var id: String,
    var todoItems: List<ToDoItem>
)