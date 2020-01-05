package com.app.development.todolist.repository

import com.app.development.todolist.service.CalendarApi
import com.app.development.todolist.service.CalendarApiService
import com.google.api.client.util.DateTime

/** The repository for getting Calendar Data from Google*/
class CalendarRepository {

    /** Implements CalendarApiService interface*/
    private val calendarApiService: CalendarApiService = CalendarApi.createApi()

    /**
     * Return a [CalendarList] object from Google with the [accessToken] from the logged in User.
     * Uses the generated [apiKey] from Google for access to their Api.
     */
    suspend fun getListOfCalendars(apiKey: String, accessToken: String) =
        calendarApiService.getListOfCalendars(apiKey, accessToken)

    /**
     * Returns a [EventList] object from Google with the [calendarId] which the user has chosen.
     * Uses the [apiKey] from Google, [orderBy] is always 'startTime' and [dateTime] is the current time and day.
     * The [accessToken] grants access to the logged in users data.
     */
    suspend fun getListOfEvents(
        calendarId: String,
        orderBy: String,
        dateTime: DateTime,
        apiKey: String,
        accessToken: String
    ) =
        calendarApiService.getListOfEvents(calendarId, orderBy, dateTime, apiKey, true, accessToken)


}
