package com.app.development.todolist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.util.Util
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter (private val events: List<EventItem>, private val allToDos:List<ToDoList>, private val onClick:(EventItem) -> Unit):RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_event,parent,false)
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
            val currentToDoList = allToDos.find {it.id == events[adapterPosition].id }
            val toDoAmount = currentToDoList?.todoItems?.size
            if (toDoAmount != null) itemView.tvAmount.text = context.getString(R.string.todo_amount,toDoAmount.toString())
            else itemView.tvAmount.text = context.getString(R.string.no_todo_amount)
            itemView.tvTime.text = Util.getEventTime(eventItem,context)
            itemView.tvSummary.text = eventItem.summary
        }
    }

}