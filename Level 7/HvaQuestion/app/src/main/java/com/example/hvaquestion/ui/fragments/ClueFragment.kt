package com.example.hvaquestion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquestion.ClueFragmentArgs
import com.example.hvaquestion.ClueFragmentDirections
import com.example.hvaquestion.R
import com.example.hvaquestion.repository.QuestRepository
import kotlinx.android.synthetic.main.fragment_clue.*


class ClueFragment : Fragment() {

    private val args: ClueFragmentArgs by navArgs()
    private val questionRepository = QuestRepository()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        ivLocation.setImageDrawable(view?.resources?.getDrawable(args.locationImage,null))
        val questions = questionRepository.getHvaQuest()
        if(questions.getOrNull(args.questionNumber + 1) != null){
            btnNext.setOnClickListener{
                val action =
                    ClueFragmentDirections.actionClueFragmentToQuestionFragment(
                        args.questionNumber + 1
                    )
                findNavController().navigate(action)
            }
        }else{
            btnNext.text = getString(R.string.finish)
            btnNext.setOnClickListener{
                findNavController().navigate(R.id.action_clueFragment_to_finishFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = findNavController()
        return when (item?.itemId) {
            android.R.id.home -> {
                navController.navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
