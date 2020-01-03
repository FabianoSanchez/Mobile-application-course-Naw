package com.app.development.todolist.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.CalendarItem
import kotlinx.android.synthetic.main.calendar_item.view.*

class AddCalendarAdapter (private val calendars: List<CalendarItem>) : RecyclerView.Adapter<AddCalendarAdapter.ViewHolder>(){

    lateinit var context: Context
    private var lastCheckedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        calendars[lastCheckedPosition].isSelected = true
        context = parent.context
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
            itemView.tvDescription.text = calendarItem.description
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