package com.app.development.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GoogleCalendar(
    var title:String,
    @PrimaryKey var id :Long? = null
)