package com.example.shoppinglist.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "productTable")
data class Product(
    @ColumnInfo(name = "amount")
    var amount: Int,

    @ColumnInfo(name = "product")
    var product: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
) : Parcelable