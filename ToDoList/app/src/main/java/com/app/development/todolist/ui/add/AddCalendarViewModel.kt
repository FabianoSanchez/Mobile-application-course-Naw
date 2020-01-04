package com.app.development.todolist.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.model.CalendarList
import com.app.development.todolist.model.Token
import com.app.development.todolist.repository.CalendarRepository
import com.app.development.todolist.repository.TokenAuthenticationsRepository
import com.app.development.todolist.util.Preference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

public class AddCalendarViewModel(application: Application) : AndroidViewModel(application) {
    private val calendarRepository = CalendarRepository()
    private val context = getApplication<Application>().applicationContext
    private val tokenAuthenticationsRepository = TokenAuthenticationsRepository(application)
    private val apiKey = context.getString(R.string.api_key)

    val calendar =  MutableLiveData<List<CalendarItem>>()

    public fun getListOfCalendars() {
        val accessToken =
            context.getSharedPreferences(Preference.PREFS_FILENAME, Preference.PRIVATE_MODE)
                .getString(Preference.ACCESS_TOKEN, "")

        CoroutineScope(Dispatchers.IO).launch {
            val response = calendarRepository.getListOfCalendars(apiKey, accessToken!!)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        calendar.apply { value = response.body()?.item }
                        println("Received Response")
                    } else if (response.code() == 401) {
                        println("Refreshing Token : ${response.code()}")
                        tokenAuthenticationsRepository.refreshToken()
                        getListOfCalendars()
                    }
                } catch (e: HttpException) {
                    println("Http Exception ${e.message()}")
                    e.printStackTrace()
                } catch (e: Throwable) {
                    println("Oops Something went wrong")
                    e.printStackTrace()
                }
            }
        }

    }

}