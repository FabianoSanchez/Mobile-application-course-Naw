package com.app.development.todolist.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.repository.CalendarRepository
import com.app.development.todolist.repository.TokenAuthenticationRepository
import com.app.development.todolist.util.Preference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/** ViewModel for AddCalendarActivity */
public class AddCalendarViewModel(application: Application) : AndroidViewModel(application) {
    private val calendarRepository = CalendarRepository()
    private val context = getApplication<Application>().applicationContext
    private val tokenAuthenticationsRepository = TokenAuthenticationRepository(application)
    private val apiKey = context.getString(R.string.api_key)

    private val unauthorizedCallCode = 401

    /** MutableLiveData which get observer by AddCalendarActivity */
    val calendar = MutableLiveData<List<CalendarItem>>()


    /**
     *  Implementation of POST request in [CalendarRepository.getListOfCalendars].
     *  Gets the accessToken from SharedPreferences
     *  Uses CoroutineScopes to execute the POST request and return a [CalendarList] object.
     *  Applies the list of [CalendarItem] to [calendar] if [response] isSuccesful.
     *  if [response.code()] return [unauthorizedCallCode] then [refreshToken()] and execute the POST request again.
     */
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
                    } else if (response.code() == unauthorizedCallCode) {
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