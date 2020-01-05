package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName

/** Data class for [EventList]. Uses a list of [EventItem]*/
data class EventList(
    @SerializedName("items") var items: List<EventItem>,
    var dateTime: String? = null
)