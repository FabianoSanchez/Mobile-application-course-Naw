package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName

data class CalendarItem(
    @SerializedName("id") var calendarId: String,
    @SerializedName("summary") var summary:String,
    @SerializedName("description") var description:String,
    var isSelected:Boolean = false
)