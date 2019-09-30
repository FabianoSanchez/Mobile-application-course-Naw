package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizzes = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(quizzes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        rvQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,false)
        rvQuiz.adapter = quizAdapter
        for (i in Quiz.answer.indices){
            quizzes.add(Quiz(Quiz.quiz[i],Quiz.answer[i]))
        }
        quizAdapter.notifyDataSetChanged()
        rvQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuiz)
    }

    private fun createItemTouchHelper(): ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val swipeAnswer = direction == 4
                if(quizzes[position].answer == swipeAnswer){
                    Snackbar.make(appView,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
                }else {
                    Snackbar.make(appView,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
                }
                quizAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }


        }
        return ItemTouchHelper(callback)
    }




}
