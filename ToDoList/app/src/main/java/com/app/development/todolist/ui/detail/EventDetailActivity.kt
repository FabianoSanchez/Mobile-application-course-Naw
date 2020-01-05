package com.app.development.todolist.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.model.ToDo
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.ui.home.HomeFragment
import com.app.development.todolist.util.Util
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: EventDetailViewModel
    private lateinit var eventItem:EventItem
    private lateinit var toDoList: ToDoList
    private var toDos = arrayListOf<ToDo>()
    private var toDoAdapter = ToDoAdapter(toDos,{listOfToDo -> updateToDoList(listOfToDo)})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        supportActionBar?.title = "Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }


    private fun initViews(){
        rvToDo.layoutManager = LinearLayoutManager(this@EventDetailActivity,LinearLayoutManager.VERTICAL,false)
        rvToDo.adapter = toDoAdapter
        rvToDo.addItemDecoration(DividerItemDecoration(this@EventDetailActivity,DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvToDo)

        eventItem = intent.extras?.getParcelable(HomeFragment.EXTRA_EVENT)!!
        tvSummary.text = eventItem.summary
        tvDate.text = Util.getEventTime(eventItem,this)
        toDoList = ToDoList(eventItem.id, arrayListOf())
        fabAdd.setOnClickListener{
            addToDoItem()
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(EventDetailViewModel::class.java)
        viewModel.getListOfToDo(eventItem.id)
        viewModel.toDo.observe(this, Observer { toDoList ->
            this@EventDetailActivity.toDos.clear()
            if(toDoList != null) {
                this@EventDetailActivity.toDos.addAll(toDoList.todoItems)
            }
            toDoAdapter.notifyDataSetChanged()
        })
    }

    private fun addToDoItem(){
        if(etAddToDo.text.isNullOrEmpty()){
            Toast.makeText(this,"Please fill in a Todo",Toast.LENGTH_LONG).show()
        }else {
            val addText = etAddToDo.text.toString()
            toDos.add(ToDo(addText,false))
            toDoList.todoItems = toDos
            viewModel.insertToDo(toDoList)
            etAddToDo.setText("")
        }
    }

    private fun updateToDoList(toDos:List<ToDo>? = null){
        toDoList.todoItems = if(toDos.isNullOrEmpty()) this.toDos else toDos
        viewModel.insertToDo(toDoList)
    }


    private fun createItemTouchHelper():ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                toDos.removeAt(position)
                updateToDoList()
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
