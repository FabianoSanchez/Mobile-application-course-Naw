package com.example.shoppinglist.database

import android.content.Context
import com.example.shoppinglist.model.Product

class ProductRepository(context: Context){

    private val productDao: ProductDao

    init{
        val database = ListRoomDatabase.getDatabase(context)
        productDao = database!!.productDao()
    }

    suspend fun getAllProducts():List<Product>{
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product:Product){
        return productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product:Product){
        return productDao.deleteProduct(product)
    }

    suspend fun deleteAllProducts(){
        return productDao.deleteAllProducts()
    }

}