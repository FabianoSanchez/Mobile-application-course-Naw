package com.app.development.todolist.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.api.client.util.DateTime
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class EventItem(
    @SerializedName("id") var id:String,
    @SerializedName("summary") var summary:String,
    @SerializedName("description") var description: String?,
    @SerializedName("start") var start: EventTime,
    @SerializedName("end") var end : EventTime,
    @SerializedName("location") var location:String?

): Parcelable

@Parcelize
data class EventTime(
    @SerializedName("date") var date: String?,
    @SerializedName("dateTime")var dateTime: String?
):Parcelable