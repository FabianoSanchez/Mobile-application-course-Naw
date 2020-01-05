package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName

/** Data object for a [CalendarList]. Consists of a list of [CalendarItem]*/
data class CalendarList(
    @SerializedName("items") var item: List<CalendarItem>
)