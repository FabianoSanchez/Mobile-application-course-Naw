package com.app.development.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.development.todolist.model.GoogleCalendar

@Dao
interface CalendarDao{

    @Query("SELECT * FROM GoogleCalendar")
    fun getCalendarEvents(): LiveData<GoogleCalendar>

    @Insert
    suspend fun insertCalendarEvent(googleCalendar:GoogleCalendar)

    @Delete
    suspend fun deleteCalender(googleCalendar:GoogleCalendar)
}