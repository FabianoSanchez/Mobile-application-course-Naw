package com.app.development.todolist.service

import com.app.development.todolist.model.CalendarList
import com.app.development.todolist.model.EventList
import com.google.api.client.util.DateTime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CalendarApiService {


    /**
     *  A GET request for Google Calendar which returns a Response of [CalendarList], using the [apiKey] and [accessToken]
     * The [accessToken] grants access to the users information and the [apiKey] allow access to the Google Api
     */
    @GET("users/me/calendarList")
    suspend fun getListOfCalendars(
        @Query("key") apiKey: String,
        @Query("access_token") accessToken: String
    ): Response<CalendarList>


    /**
     * A GET request for Google Calendar which returns a Response of [EventList], using the [accessToken] and [apiKey]
     * @param calendarId    an ID which identifies the selected Calendar for Google Calendar
     * @param orderBy       a hardcoded string: 'startTime'. This orders the response by start time of event
     * @param dateTime      DateTime.now() which has been formatted for rfc3339 format
     * @param apiKey        a generated key by Google
     * @param singleEvents  a Boolean which is always true, otherwise orderBy doesn't function correctly
     * @param accessToken   the token which gives access to the logged in users data
     * @return Response<EventList>
     * */
    @GET("calendars/{calendarId}/events")
    suspend fun getListOfEvents(
        @Path("calendarId") calendarId: String,
        @Query("orderBy") orderBy: String,
        @Query("timeMin") dateTime: DateTime,
        @Query("key") apiKey: String,
        @Query("singleEvents") singleEvents: Boolean = true,
        @Query("access_token") accessToken: String
    ): Response<EventList>

}