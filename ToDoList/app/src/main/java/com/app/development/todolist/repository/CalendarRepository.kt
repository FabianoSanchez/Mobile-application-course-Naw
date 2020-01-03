package com.app.development.todolist.repository

import com.app.development.todolist.service.CalendarApi
import com.app.development.todolist.service.CalendarApiService
import com.app.development.todolist.service.TokenAuthentication
import com.app.development.todolist.service.TokenAuthenticationService

class CalendarRepository {

    private val calendarApiService: CalendarApiService = CalendarApi.createApi()

    fun getListOfCalendars(apiKey:String,accessToken:String) =
        calendarApiService.getListOfCalendars(apiKey,accessToken)

}
