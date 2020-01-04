package com.app.development.todolist.ui.detail

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.ToDo
import kotlinx.android.synthetic.main.todo_item.view.*





class ToDoAdapter (private val toDos:List<ToDo>,private val onClick:(List<ToDo>) -> Unit): RecyclerView.Adapter<ToDoAdapter.ViewHolder>(){
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        )
    }

    override fun getItemCount(): Int = toDos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cbToDo.isChecked = toDos[position].isCompleted
        holder.itemView.cbToDo.text = toDos[position].toDo
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init{
            itemView.cbToDo.setOnCheckedChangeListener { _, selected ->
                println("toDOs $toDos")
                toDos[adapterPosition].isCompleted = selected
                if(selected)itemView.cbToDo.paintFlags = (itemView.cbToDo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
                else itemView.cbToDo.paintFlags = (itemView.cbToDo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                onClick(toDos)
            }
        }
    }
}