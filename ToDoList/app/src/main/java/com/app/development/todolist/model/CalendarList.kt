package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName

data class CalendarList(
    @SerializedName("items") var item : List<CalendarItem>
)