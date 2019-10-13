package com.example.shoppinglist.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.database.ProductRepository
import com.example.shoppinglist.model.Product

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val shoppingList = arrayListOf<Product>()
    private val productAdapter = ProductAdapter(shoppingList)
    private lateinit var productRepository: ProductRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Shopping List"
        supportActionBar?.show()
        productRepository = ProductRepository(this)
        initViews()
    }

    private fun initViews(){
        rvList.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvList.adapter = productAdapter
        rvList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvList)
        getShoppingListFromDatabse()
        fab.setOnClickListener{addProduct()}
    }

    private fun createItemTouchHelper(): ItemTouchHelper{
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
                val productToDelete = shoppingList[position]
                mainScope.launch{
                    withContext(Dispatchers.IO){
                        productRepository.deleteProduct(productToDelete)
                    }
                    getShoppingListFromDatabse()
                }
            }
        }
        return ItemTouchHelper(callback)
    }


    private fun addProduct(){
        if(validateFields()){
            val product = Product(amountInput.text.toString().toInt(),productInput.text.toString())
            mainScope.launch {
                withContext(Dispatchers.IO){
                       productRepository.insertProduct(product)
                }
                getShoppingListFromDatabse()
            }
        }
    }

    private fun validateFields(): Boolean{
        return if(productInput.text.toString().isNotBlank() && amountInput.text.toString().isNotBlank()){
            true
        }else{
            Toast.makeText(this,"The fields are empty, please fill them in",Toast.LENGTH_SHORT).show()
             false
        }
    }

    private fun getShoppingListFromDatabse(){
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO){
                productRepository.getAllProducts()
            }
            this@MainActivity.shoppingList.clear()
            this@MainActivity.shoppingList.addAll(shoppingList)
            this@MainActivity.productAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteShoppingList(){
        mainScope.launch {
            withContext(Dispatchers.IO){
                productRepository.deleteAllProducts()
            }
            this@MainActivity.shoppingList.clear()
            this@MainActivity.productAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                deleteShoppingList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }




}
