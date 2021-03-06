package com.app.development.todolist.ui.todo

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.ToDoItem
import kotlinx.android.synthetic.main.item_all_todo.view.*

class AllToDoAdapter(private val allToDoItems: List<ToDoItem>) :
    RecyclerView.Adapter<AllToDoAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_todo, parent, false)
        )
    }

    override fun getItemCount(): Int = allToDoItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allToDoItems[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toDoItem: ToDoItem) {
            itemView.tvToDo.text = toDoItem.toDo
            if (toDoItem.isCompleted) {
                itemView.tvToDo.paintFlags =
                    (itemView.tvToDo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
                itemView.ivCompleted.setImageDrawable(context.getDrawable(R.drawable.ic_check_circle_green_24dp))
            } else itemView.ivCompleted.visibility = View.GONE
        }
    }
}