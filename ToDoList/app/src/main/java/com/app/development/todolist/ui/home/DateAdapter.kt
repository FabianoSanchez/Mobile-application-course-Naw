package com.app.development.todolist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.model.EventList
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.util.Util
import kotlinx.android.synthetic.main.item_date.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateAdapter(private val dates:  List<EventList>, private val allToDos:List<ToDoList>, private val onClick:(EventItem) -> Unit): RecyclerView.Adapter<DateAdapter.ViewHolder>(){

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_date,parent,false)
        )
    }

    override fun getItemCount(): Int = dates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(dates[position])
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(eventList: EventList){
            val eventAdapter = EventAdapter(dates[adapterPosition].items,allToDos,{eventItem -> onClick(eventItem)})
            itemView.rvEvents.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = eventAdapter
            }
            eventAdapter.notifyDataSetChanged()
            val dateTime = LocalDate.parse(eventList.dateTime)
            val suffix = Util.daySuffixForDate(dateTime.dayOfMonth)
            val formatter = DateTimeFormatter.ofPattern("EEEE dd'$suffix' MMMM yyyy").withLocale(
                Locale.ENGLISH)
            itemView.tvDate.text = formatter.format(dateTime)
        }
    }

}