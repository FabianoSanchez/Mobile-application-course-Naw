package com.app.development.todolist.ui.todo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.development.todolist.R
import com.app.development.todolist.model.ToDoItem
import com.app.development.todolist.model.ToDoList
import kotlinx.android.synthetic.main.fragment_to_do.*


/***
 * A simple [Fragment] subclass.
 */
class ToDoFragment : Fragment() {

    private lateinit var viewModel: ToDoViewModel
    private var allToDos = arrayListOf<ToDoItem>()
    private var toDoAdapter = AllToDoAdapter(allToDos)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_calendar).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun initViews() {
        rvAllToDos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvAllToDos.adapter = toDoAdapter
        rvAllToDos.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        viewModel.toDos.observe(this, Observer {
            if (it.isNotEmpty()) {
                tvEmptyrv.visibility = View.GONE
            }
            this@ToDoFragment.allToDos.clear()
            this@ToDoFragment.allToDos.addAll(getAllToDoFromToDoList(it))
            toDoAdapter.notifyDataSetChanged()
        })
    }

    private fun getAllToDoFromToDoList(toDoList: List<ToDoList>): List<ToDoItem> {
        val list = mutableListOf<ToDoItem>()
        toDoList.forEach { _toDoList ->
            _toDoList.todoItems.forEach {
                list.add(it)
            }
        }
        list.sortBy { it.isCompleted }
        return list
    }
}
