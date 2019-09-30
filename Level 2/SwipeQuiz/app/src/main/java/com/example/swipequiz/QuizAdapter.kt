package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*

class QuizAdapter(private val quizzes : List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          return ViewHolder(
              LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false)
          )
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quizzes[position])
    }


    inner class ViewHolder(itemView: View):
     RecyclerView.ViewHolder(itemView){
        private val tvQuiz : TextView = itemView.findViewById(android.R.id.text1)
        fun bind(quiz : Quiz){
           tvQuiz.text = quiz.quiz
        }
    }

}