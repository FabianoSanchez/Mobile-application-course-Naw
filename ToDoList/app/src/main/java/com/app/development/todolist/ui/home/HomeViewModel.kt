package com.app.development.todolist.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarList
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.repository.CalendarRepository
import com.app.development.todolist.repository.TokenAuthenticationsRepository
import com.app.development.todolist.util.Preference
import com.google.api.client.util.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val context = getApplication<Application>().applicationContext
    private val calendarRepository = CalendarRepository()
    private val prefs = context.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
    private val tokenAuthenticationsRepository = TokenAuthenticationsRepository(context)
    private val apiKey = context.resources.getString(R.string.api_key)
    private val orderBy = "startTime"

    val events = MutableLiveData<List<EventItem>>()



    public fun getListOfEvents(){
        val calendarId = prefs.getString(Preference.CALENDAR_ID,"")!!
        val accessToken = prefs.getString(Preference.ACCESS_TOKEN,"")!!
        val dateTime = DateTime.parseRfc3339(LocalDateTime.now().toString())
        println(dateTime)

        CoroutineScope(Dispatchers.IO).launch {
           val response = calendarRepository.getListOfEvents(calendarId,orderBy,dateTime,apiKey,accessToken)
            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful){
                        events.apply{value = response.body()?.items}
                        println("Received Response")
                    }else if(response.code() == 401){
                        println("Refreshing Token : ${response.code()}")
                        tokenAuthenticationsRepository.refreshToken()
                        getListOfEvents()
                    }
                }catch (e: HttpException){
                    println("Oops ${e.message()}")
                    e.printStackTrace()
                }catch (e: Throwable){
                    println("Oops Something went wrong")
                    e.printStackTrace()
                }
            }

        }

    }



}