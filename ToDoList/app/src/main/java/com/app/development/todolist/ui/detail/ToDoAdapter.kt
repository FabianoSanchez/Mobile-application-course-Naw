package com.app.development.todolist.ui.detail

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.ToDoItem
import kotlinx.android.synthetic.main.item_todo.view.*

/** Adapter for the [rvToDo] in EventDetailActivity
 * @param toDoItems a list to show in the RecyclerView
 * @param onClick the function to execute when clicking on RecyclerView element
 */
class ToDoAdapter(
    private val toDoItems: List<ToDoItem>,
    private val onClick: (List<ToDoItem>) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount(): Int = toDoItems.size

    /**
     * Set the isChecked value of [cbToDo] to the isCompleted value of the [ToDoItem]
     * Set the text of [cbToDo] to [ToDoItem.toDo]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cbToDo.isChecked = toDoItems[position].isCompleted
        holder.itemView.cbToDo.text = toDoItems[position].toDo
    }

    /** Initialize the onCheckedChange for [cbToDo]*/
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            /**
             * Set [ToDoItem.isCompleted] to the value of [selected]
             * Strike Through the text if toDoItem isCompleted
            */
            itemView.cbToDo.setOnCheckedChangeListener { _, selected ->
                toDoItems[adapterPosition].isCompleted = selected
                if (selected) itemView.cbToDo.paintFlags =
                    (itemView.cbToDo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
                else itemView.cbToDo.paintFlags =
                    (itemView.cbToDo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                onClick(toDoItems)
            }
        }
    }
}