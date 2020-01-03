package com.app.development.todolist.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.development.todolist.model.GoogleCalendar
import com.app.development.todolist.model.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@Database(entities = [GoogleCalendar::class,Token::class], version = 1,exportSchema = false)
abstract class CalendarRoomDatabase : RoomDatabase(){

    abstract fun calendarDao(): CalendarDao
    abstract fun tokenDao():TokenDao

    companion object{
        private const val DATABASE_NAME = "CALENDAR_DATABASE"

        @Volatile
        private var INSTANCE : CalendarRoomDatabase? = null

        fun getDatabase(context: Context): CalendarRoomDatabase?{
            if(INSTANCE == null){
                synchronized(CalendarRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CalendarRoomDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}