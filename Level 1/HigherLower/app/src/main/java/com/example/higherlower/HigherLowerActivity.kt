package com.example.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.acitivity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private var currentThrow: Int = 1;
    private var lastThrow: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_higher_lower)
        lowerButton.setOnClickListener{onLowerClick()}
        higherButton.setOnClickListener{onHigherClick()}
        equalsButton.setOnClickListener{onEqualClick()}

        initViews()

    }

    private fun initViews(){
        updateUI();
    }

    private fun onHigherClick(){
        rollDice()
        if(lastThrow < currentThrow){
            onAnswerCorrect()
        }else{
            onAnswerIncorrect()
        }
    }

    private fun onLowerClick(){
        rollDice()
        if(lastThrow > currentThrow){
            onAnswerCorrect()
        }else{
            onAnswerIncorrect()
        }
    }

    private fun onEqualClick(){
        rollDice()
        if(lastThrow == currentThrow){
            onAnswerCorrect()
        }else{
            onAnswerIncorrect()
        }
    }

    private fun onAnswerCorrect(){
        Toast.makeText(this,getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    private fun onAnswerIncorrect(){
        Toast.makeText(this,getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }

    private fun updateUI() {
        lastValue.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> diceImage.setImageResource(R.drawable.dice1)
            2 -> diceImage.setImageResource(R.drawable.dice2)
            3 -> diceImage.setImageResource(R.drawable.dice3)
            4 -> diceImage.setImageResource(R.drawable.dice4)
            5 -> diceImage.setImageResource(R.drawable.dice5)
            6 -> diceImage.setImageResource(R.drawable.dice6)
        }
    }


    private fun rollDice(){
        lastThrow = currentThrow;
        currentThrow = (1..6).random()
        updateUI()
    }

}
