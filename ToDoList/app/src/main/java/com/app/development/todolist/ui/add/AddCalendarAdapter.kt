package com.app.development.todolist.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.util.Preference
import kotlinx.android.synthetic.main.item_calendar.view.*

/* Adapter for the recyclerView when selecting a Calendar*/
class AddCalendarAdapter(private val calendars: List<CalendarItem>) :
    RecyclerView.Adapter<AddCalendarAdapter.ViewHolder>() {

    lateinit var context: Context
    private var lastCheckedPosition = 0
    private var lock = true

    /**
     * Initialize context and check if CalenderId exists in SharedPreferences
     * If calendarId already exists, then select the already existing calendarId radioButton.
     * If it doesn't exist, then just select the first option
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val prefs = context.getSharedPreferences(Preference.PREFS_FILENAME, Preference.PRIVATE_MODE)
        val calendarId = prefs.getString(Preference.CALENDAR_ID, "")
        if (calendarId != "" && lock) {
            lastCheckedPosition =
                calendars.indexOf(calendars.find { calendarItem -> calendarId == calendarItem.calendarId })
            lock = false
        }
        /* Change the [CalendarItem] isSelected atribute to true, so that Intent can return the correct CalendarId */
        calendars[lastCheckedPosition].isSelected = true
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        )
    }

    override fun getItemCount(): Int = calendars.size

    /** Bind the Viewholder to the [CalendarItem]. Check if the RadioButton is selected.*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.rbCalendar.isChecked = (position == lastCheckedPosition)

        holder.bind(calendars[position])
    }

    /**
     * Bind [tvDate] to [CalendarItem.description]
     * Bind [rbCalendar] to [CalendarItem.summary]
     * Set cardView onClick to change the selected RadioButton, and refresh the recyclerView.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(calendarItem: CalendarItem) {
            itemView.tvDate.text = calendarItem.description
            itemView.rbCalendar.text = calendarItem.summary
            itemView.mcView.setOnClickListener {
                calendars[lastCheckedPosition].isSelected = false
                calendars[adapterPosition].isSelected = true
                lastCheckedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}