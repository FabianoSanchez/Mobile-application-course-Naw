package com.app.development.todolist.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.ui.add.AddCalendar
import com.app.development.todolist.util.Preference
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val events = arrayListOf<EventItem>()
    private val eventAdapter = HomeAdapter(events)
    private lateinit var prefs:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModels()
        checkCalendarId()
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        rvEvents.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvEvents.adapter = eventAdapter
    }

    private fun initViewModels(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.events.observe(this, Observer {
            this@HomeFragment.events.clear()
            this@HomeFragment.events.addAll(it)
            eventAdapter.notifyDataSetChanged()
        })
    }


    private fun checkCalendarId(){
        val calendarId = prefs?.getString(Preference.CALENDAR_ID,"")
        if(calendarId.isNullOrEmpty()){
            runCalendarIntent()
        }else{
            initCalendarData(calendarId)
        }
    }

    private fun runCalendarIntent(){
        val intent = Intent(context, AddCalendar::class.java)
        startActivityForResult(intent, HomeActivity.ADD_CALENDAR_REQUEST_CODE)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_calendar ->{
                runCalendarIntent()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                HomeActivity.ADD_CALENDAR_REQUEST_CODE ->{
                    val calendarId = data!!.getStringExtra(AddCalendar.EXTRA_CALENDAR_ID)
                    prefs.edit().putString(Preference.CALENDAR_ID,calendarId).apply()
                    initCalendarData(calendarId!!)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initCalendarData(calendarId: String){
        println("get Calendar1 $calendarId")
        println("get Calendar ${prefs.getString(Preference.CALENDAR_ID,"")}")
        homeViewModel.getListOfEvents()
        eventAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        prefs = context!!.getSharedPreferences(Preference.PREFS_FILENAME,Preference.PRIVATE_MODE)
    }


}
