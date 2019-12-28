package com.example.recyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.model.ColorItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val colors = arrayListOf<ColorItem>()
    private val adapter = ColorAdapter(colors,{colorItem -> onColorClick(colorItem)})
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
    }

    private fun initViews(){
        rvColors.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvColors.adapter = adapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.colorItems.observe(this, Observer {
            this@MainActivity.colors.clear()
            this@MainActivity.colors.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun onColorClick(colorItem:ColorItem){
        Snackbar.make(rvColors,"This color is ${colorItem.name}",Snackbar.LENGTH_LONG).show()
    }
}
