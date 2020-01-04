package com.app.development.todolist.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class EventList(
    @SerializedName("items")var items: List<EventItem>,
                                  var dateTime: String? = null
)