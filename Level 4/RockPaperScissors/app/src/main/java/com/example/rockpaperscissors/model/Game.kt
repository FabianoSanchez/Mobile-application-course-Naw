package com.example.rockpaperscissors.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rockpaperscissors.R
import kotlinx.android.parcel.Parcelize
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
@TypeConverters(Converters::class)
data class Game(

    @ColumnInfo(name = "timeStamp")
    var timeStamp: OffsetDateTime,

    @ColumnInfo(name = "computerMove")
    @DrawableRes var computerResId:Int,

    @ColumnInfo(name = "playerMove")
    @DrawableRes var playerResId:Int,

    @ColumnInfo(name = "result")
    var result : Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long? = null

): Parcelable
{
    companion object{
        val PLAYS = arrayOf(
            "ROCK",
            "SCISSORS",
            "PAPER"
        )
        val PLAYS_RES_DRAWABLE_IDS = arrayOf(
            R.drawable.rock,
            R.drawable.paper,
            R.drawable.scissors
        )
    }
}