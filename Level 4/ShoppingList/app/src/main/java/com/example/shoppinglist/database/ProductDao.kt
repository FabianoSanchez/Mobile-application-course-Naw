package com.example.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglist.model.Product

@Dao
interface ProductDao{
    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM productTable")
    suspend fun getAllProducts(): List<Product>

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM productTable")
    suspend fun deleteAllProducts()
}