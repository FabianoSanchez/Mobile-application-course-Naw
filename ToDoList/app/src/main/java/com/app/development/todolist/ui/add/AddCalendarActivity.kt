package com.app.development.todolist.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.util.Preference
import kotlinx.android.synthetic.main.activity_add_calendar.*


/**
 * Activity for selecting a Calendar.
 */
class AddCalendarActivity : AppCompatActivity() {

    /** Initializing the variables.*/
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

    /** Initializes the RecyclerView, and sets the onClick for the Floating action button*/
    private fun initViews() {
        rvCalendar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCalendar.adapter = calendarAdapter
        fabSave.setOnClickListener {

            /* Get the selected CalendarID, and start finish the intent, with calendarId as extra data */
            val calendar = calendars.find { it.isSelected }
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_CALENDAR_ID, calendar?.calendarId)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    /** Initialize the viewmodel*/
    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(AddCalendarViewModel::class.java)
        viewModel.getListOfCalendars()
        /* Observe the calendar and refresh recyclerView when calendar changes.*/
        viewModel.calendar.observe(this, Observer {
            this@AddCalendarActivity.calendars.clear()
            this@AddCalendarActivity.calendars.addAll(it)
            calendarAdapter.notifyDataSetChanged()
        })
    }


    /**
     *Checks if there is already a CalendarId in the SharedPreference.
     *If there is a calendarID, then the back button is allowed. Otherwise the backbutton doesn't do anything
     */
    override fun onBackPressed() {
        val prefs = applicationContext.getSharedPreferences(
            Preference.PREFS_FILENAME,
            Preference.PRIVATE_MODE
        )
        if (prefs.getString(Preference.CALENDAR_ID, "").isNullOrEmpty()) {
            return
        } else {
            super.onBackPressed()
        }
    }

    /** Constant for Calendar Intent*/
    companion object {
        const val EXTRA_CALENDAR_ID = "EXTRA_CALENDAR_ID"
    }
}
