package com.app.development.todolist.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.model.CalendarList
import com.app.development.todolist.model.Token
import com.app.development.todolist.repository.CalendarRepository
import com.app.development.todolist.util.Preference
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class AddCalendarViewModel(application: Application) : AndroidViewModel(application) {
    private val calendarRepository = CalendarRepository()
    private val context = getApplication<Application>().applicationContext
    private val apiKey = context.getString(R.string.api_key)
    private lateinit var token: Token

    val calendar =  MutableLiveData<List<CalendarItem>>()



    public fun getListOfCalendars(){
        val accessToken = context.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE).getString(Preference.ACCESS_TOKEN,"")
        println("AccesTOken $accessToken")
        calendarRepository.getListOfCalendars(apiKey,accessToken!!).enqueue(object:
            Callback<CalendarList> {
            override fun onFailure(call: Call<CalendarList>, t: Throwable) {
                t.printStackTrace()

            }

            override fun onResponse(call: Call<CalendarList>, response: Response<CalendarList>) {
                try{
                    if(response.isSuccessful){
                        calendar.apply{value = response.body()?.item}
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }
        })
    }

}