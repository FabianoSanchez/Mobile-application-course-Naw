package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName

data class EventList(
    @SerializedName("items")var items: List<EventItem>
)