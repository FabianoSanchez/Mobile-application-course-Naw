package com.app.development.todolist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.repository.ToDoRepository
import com.app.development.todolist.util.Util
import kotlinx.android.synthetic.main.date_item.view.*
import kotlinx.android.synthetic.main.event_item.view.*

class EventAdapter (private val events: List<EventItem>,private val onClick:(EventItem) -> Unit):RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_item,parent,false)
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(events[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init{
            itemView.setOnClickListener{
                onClick(events[adapterPosition])
            }
        }


        fun bind(eventItem: EventItem){

            itemView.tvTime.text = Util.getEventTime(eventItem,context)
            itemView.tvSummary.text = eventItem.summary
        }
    }

}