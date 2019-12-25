package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Game (
    var title: String,
    var releaseDate: Date,
    var platform: String,
    @PrimaryKey var id:Long? =null
):Parcelable
