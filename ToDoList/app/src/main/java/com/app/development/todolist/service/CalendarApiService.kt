package com.app.development.todolist.service

import com.app.development.todolist.model.CalendarList
import com.google.api.services.calendar.CalendarScopes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CalendarApiService {

    @GET("calendarList")
    fun getListOfCalendars(@Query("key") apiKey: String,
                           @Query("access_token") accessToken:String) : Call<CalendarList>




}