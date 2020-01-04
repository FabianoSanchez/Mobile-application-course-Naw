package com.app.development.todolist.repository

import com.app.development.todolist.service.CalendarApi
import com.app.development.todolist.service.CalendarApiService
import com.google.api.client.util.DateTime

class CalendarRepository {

    private val calendarApiService: CalendarApiService = CalendarApi.createApi()


    fun getListOfCalendars(apiKey:String,accessToken:String) =
        calendarApiService.getListOfCalendars(apiKey,accessToken)

    suspend fun getListOfEvents(calendarId:String,orderBy:String,dateTime: DateTime,apiKey: String,accessToken: String) =
        calendarApiService.getListOfEvents(calendarId,orderBy,dateTime,apiKey,true,accessToken)

}
