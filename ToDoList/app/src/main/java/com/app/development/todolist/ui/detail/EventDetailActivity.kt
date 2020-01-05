package com.app.development.todolist.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.model.ToDoItem
import com.app.development.todolist.model.ToDoList
import com.app.development.todolist.ui.home.HomeFragment
import com.app.development.todolist.util.Util
import kotlinx.android.synthetic.main.activity_event_detail.*


/**
 * Activity for showing the details of an EventItem
 */
class EventDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: EventDetailViewModel
    private lateinit var eventItem: EventItem
    private lateinit var toDoList: ToDoList
    private var toDos = arrayListOf<ToDoItem>()
    private var toDoAdapter = ToDoAdapter(toDos, { listOfToDo -> updateToDoList(listOfToDo) })

    /** Initialize views and viewModel. Set Title to [Event] and allow back button */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        supportActionBar?.title = "Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }

    /** Setup the recyclerView[rvToDo] and allow for Swiping of a [ToDoItem].
     *  Get the [EventItem] from the [intent.extras] and setup the View elements.
     *  Set onClick of [fabAdd] to execute [addToDoItem]
     */
    private fun initViews() {
        rvToDo.layoutManager =
            LinearLayoutManager(this@EventDetailActivity, LinearLayoutManager.VERTICAL, false)
        rvToDo.adapter = toDoAdapter
        rvToDo.addItemDecoration(
            DividerItemDecoration(
                this@EventDetailActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvToDo)

        eventItem = intent.extras?.getParcelable(HomeFragment.EXTRA_EVENT)!!
        tvSummary.text = eventItem.summary
        tvDate.text = Util.getEventTime(eventItem, this)
        /* Initialize the list of [ToDoItem] */
        toDoList = ToDoList(eventItem.id, arrayListOf())
        fabAdd.setOnClickListener {
            addToDoItem()
        }
    }

    /** Initialize the ViewModel, execute [getListofToDo()] and Observe [toDo]*/
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EventDetailViewModel::class.java)
        viewModel.getListOfToDo(eventItem.id)
        viewModel.toDo.observe(this, Observer { toDoList ->
            this@EventDetailActivity.toDos.clear()
            /* Check if there are [ToDoItem] objects, so that it doesn't throw NullPointerException  */
            if (toDoList != null) {
                this@EventDetailActivity.toDos.addAll(toDoList.todoItems)
            }
            toDoAdapter.notifyDataSetChanged()
        })
    }

    /**
     * Checks if EditText is filled in, if it is not filled in show a Toast message
     * If it is filled then add it to [toDos], and set [toDoList.toDoItems] to [toDos]
     * then execute the insert Query for [ToDoList].
     * Empty the Edit Text.
     */
    private fun addToDoItem() {
        if (etAddToDo.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please fill in a Todo", Toast.LENGTH_LONG).show()
        } else {
            val addText = etAddToDo.text.toString()
            toDos.add(ToDoItem(addText, false))
            updateToDoList(toDos)
            etAddToDo.setText("")
        }
    }

    /**
     * Update the Room database with the new changes.
     * Check if [toDoItems] == null and set [toDoItems] to the correct List
     * Insert [ToDoList]
     * @param toDoItems a List of [ToDoItem] which is nullable,
     * so that this function can be used by other functions without duplicating code.
     */
    private fun updateToDoList(toDoItems: List<ToDoItem>? = null) {
        toDoList.todoItems = if (toDoItems.isNullOrEmpty()) this.toDos else toDoItems
        viewModel.insertToDo(toDoList)
    }

    /**
     * CreateItemTouchHelper for RecyclerView [rvToDo], so that the [ToDoItem] can be deleted on swipe
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            /** Get position of the Swiped [ToDoItem] and remove it from [toDos].
             * Then execture [updateToDoList]
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                toDos.removeAt(position)
                updateToDoList()
            }
        }
        return ItemTouchHelper(callback)
    }

    /** Finish intent when tapping on the back arrow */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
