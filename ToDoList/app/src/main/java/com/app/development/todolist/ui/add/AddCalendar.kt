package com.app.development.todolist.ui.add

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import kotlinx.android.synthetic.main.activity_add_calendar.*

class AddCalendar : AppCompatActivity() {

    private lateinit var viewModel: AddCalendarViewModel
    private val calendars = arrayListOf<CalendarItem>()
    private val calendarAdapter = AddCalendarAdapter(calendars)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_calendar)
        supportActionBar?.title = "Add a calendar"
        initViews()
        initViewModels()

    }

    private fun initViews(){
        rvCalendar.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCalendar.adapter = calendarAdapter
        fabSave.setOnClickListener{
            val calendar = calendars.find {it.isSelected}
            println("calendar: $calendar")
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_CALENDAR_ID,calendar?.calendarId)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }
    }

    private fun initViewModels(){
        viewModel = ViewModelProviders.of(this).get(AddCalendarViewModel::class.java)
        viewModel.getListOfCalendars()

        viewModel.calendar.observe(this, Observer {
            this@AddCalendar.calendars.clear()
            this@AddCalendar.calendars.addAll(it)
            calendarAdapter.notifyDataSetChanged()
            println("Boi we doing it : $it")
        })
    }


    companion object{
        const val EXTRA_CALENDAR_ID = "EXTRA_CALENDAR_ID"
    }
}
