package com.app.development.todolist.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import com.app.development.todolist.util.Preference
import kotlinx.android.synthetic.main.calendar_item.view.*


class AddCalendarAdapter (private val calendars: List<CalendarItem>) : RecyclerView.Adapter<AddCalendarAdapter.ViewHolder>(){

    lateinit var context: Context
    private var lastCheckedPosition = 0
    var lock = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val prefs = context.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
        val calendarId = prefs.getString(Preference.CALENDAR_ID,"")
        if(calendarId != "" && lock){
            lastCheckedPosition  = calendars.indexOf(calendars.find{calendarItem -> calendarId == calendarItem.calendarId})
            lock = false
        }

        calendars[lastCheckedPosition].isSelected = true
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.calendar_item,parent,false)
        )
    }

    override fun getItemCount(): Int = calendars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.itemView.rbCalendar.isChecked = (position == lastCheckedPosition)

        holder.bind(calendars[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(calendarItem:CalendarItem){
            itemView.tvDate.text = calendarItem.description
            itemView.rbCalendar.text = calendarItem.summary
            itemView.mcView.setOnClickListener{
                calendars[lastCheckedPosition].isSelected = false
                calendars[adapterPosition].isSelected = true
                lastCheckedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}