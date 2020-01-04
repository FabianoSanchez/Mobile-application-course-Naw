package com.app.development.todolist.service

import com.app.development.todolist.model.CalendarList
import com.app.development.todolist.model.EventList
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.CalendarScopes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CalendarApiService {

    @GET("users/me/calendarList")
    suspend fun getListOfCalendars(@Query("key") apiKey: String,
                           @Query("access_token") accessToken:String) : Response<CalendarList>



    @GET("calendars/{path_variable}/events")
    suspend fun getListOfEvents(@Path("path_variable")calendarId: String,
                        @Query("orderBy")orderBy:String,
                        @Query("timeMin")dateTime: DateTime,
                        @Query("key")apiKey:String,
                        @Query("singleEvents")singleEvents:Boolean = true,
                        @Query("access_token")accessToken: String) : Response<EventList>

}