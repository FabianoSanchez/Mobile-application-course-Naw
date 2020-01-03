package com.app.development.todolist.util

import android.content.Context
import android.content.SharedPreferences

class Preference(private val context: Context){

    companion object{
        const val PRIVATE_MODE = 0
        const val PREFS_FILENAME = "com.app.development.todolist"
        const val REFRESH_TOKEN = "refresh_token"
        const val CALENDAR_ID = "CALENDAR_ID"
        const val USER_AUTH_ID = "USER_AUTH_ID"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"

    }
    public fun getPreference(): SharedPreferences{
        return context.getSharedPreferences(PREFS_FILENAME, PRIVATE_MODE)
    }
}