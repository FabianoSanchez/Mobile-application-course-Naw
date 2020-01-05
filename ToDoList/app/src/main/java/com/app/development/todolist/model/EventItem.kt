package com.app.development.todolist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/** Data class for [EventItem]. Uses [EvenTime] object*/
@Parcelize
data class EventItem(
    @SerializedName("id") var id: String,
    @SerializedName("summary") var summary: String,
    @SerializedName("description") var description: String?,
    @SerializedName("start") var start: EventTime,
    @SerializedName("end") var end: EventTime,
    @SerializedName("location") var location: String?

) : Parcelable


/** Data class for [EventTime] */
@Parcelize
data class EventTime(
    @SerializedName("date") var date: String?,
    @SerializedName("dateTime") var dateTime: String?
) : Parcelable