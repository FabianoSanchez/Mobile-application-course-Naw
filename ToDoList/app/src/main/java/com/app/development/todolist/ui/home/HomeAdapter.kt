package com.app.development.todolist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import kotlinx.android.synthetic.main.event_item.view.*

class HomeAdapter(private val events: List<EventItem>): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_item,parent,false)
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(events[position])

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(eventItem: EventItem){
            itemView.tvDescription.text = eventItem.description
            itemView.tvSummary.text = eventItem.summary
            itemView.tvDateTime.text = "${eventItem.start.dateTime} -  ${eventItem.end.dateTime}"
        }
    }

}