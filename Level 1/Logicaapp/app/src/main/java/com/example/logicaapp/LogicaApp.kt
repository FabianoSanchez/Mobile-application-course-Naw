package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.logica_app.*
import kotlinx.android.synthetic.main.logica_app.view.*

class LogicaApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logica_app)
        sumbitButton.setOnClickListener{sumbitAnswer()}
    }




    private fun sumbitAnswer(){
        if(answer1.text.isEmpty() || answer2.text.isEmpty() || answer3.text.isEmpty() || answer4.text.isEmpty()){
            noAnswer()
            return
        }
        if(answer1.text.toString() == getString(R.string.true_text) && answer2.text.toString() == getString(R.string.false_text)
            && answer3.text.toString() == getString(R.string.false_text) && answer4.text.toString() == getString(R.string.false_text)){
            onAnswerCorrect()
        }else{
            onAnswerIncorrect()
        }

    }

    private fun noAnswer(){
        Toast.makeText(this,getString(R.string.not_filled),Toast.LENGTH_SHORT).show()
    }

    private fun onAnswerCorrect(){
        Toast.makeText(this,getString(R.string.correct),Toast.LENGTH_SHORT).show()
    }

    private fun onAnswerIncorrect(){
        Toast.makeText(this,getString(R.string.incorrect),Toast.LENGTH_SHORT).show()
    }


}
