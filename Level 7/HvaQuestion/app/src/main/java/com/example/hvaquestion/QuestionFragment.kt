package com.example.hvaquestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private var questRepository = QuestRepository()
    private val questions = questRepository.getHvaQuest()
    private val args: QuestionFragmentArgs by navArgs()
    private var selectedAnswer = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews(){
        tvQuestionNumber.text = "${args.questionNumber + 1}/${questions.size}"
        createRadioButton()

        btnConfirm.setOnClickListener{
            println("SELECTED ANSWER : $selectedAnswer")
           if(selectedAnswer.isNotBlank()){
               checkAnswer()
           }else{
               Toast.makeText(context,"Please select an answer",Toast.LENGTH_SHORT).show()
           }
        }
    }

    private fun checkAnswer(){
        val question = questions[args.questionNumber]
        val correctAnswer = question.correctAnswer
        if(selectedAnswer == correctAnswer){
            val action = QuestionFragmentDirections.actionQuestionFragmentToClueFragment(question.clue,args.questionNumber)
            findNavController().navigate(action)
        }else{
            Toast.makeText(context,"Your answer is wrong",Toast.LENGTH_SHORT).show()
        }
    }

    private fun createRadioButton(){
        val question = questions[args.questionNumber]
        tvQuestion.text = question.question
        val radioGroup = RadioGroup(context)
        question.choices.forEach {
            val radioButton = RadioButton(context)
            radioButton.id = View.generateViewId()
            radioButton.text = it
            radioGroup.addView(radioButton)
        }
        radioGroup.setOnCheckedChangeListener{radioGroup, checkId ->
            selectedAnswer = view?.findViewById<RadioButton>(checkId)?.text.toString()
        }
        rgAnswer.addView(radioGroup)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
