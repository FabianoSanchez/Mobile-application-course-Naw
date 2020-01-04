package com.app.development.todolist.ui.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.model.ToDo

class AllToDoAdapter (private val allToDos : List<ToDo>):RecyclerView.Adapter<AllToDoAdapter.ViewHolder>(){
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false)
        )
    }

    override fun getItemCount(): Int = allToDos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allToDos[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvToDo : TextView = itemView.findViewById(android.R.id.text1)
        fun bind(toDo: ToDo){
            tvToDo.textSize = 20f
            tvToDo.text = toDo.toDo
        }
    }
}