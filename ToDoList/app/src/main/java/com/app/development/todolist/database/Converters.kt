package com.app.development.todolist.database

import androidx.room.TypeConverter
import com.app.development.todolist.model.ToDoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList


/** Converts for the List<[ToDoItem]> */
class Converters {
    private var gson = Gson()

    /** Returns a list of [ToDoItem] object, from a [String]*/
    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<ToDoItem> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<ToDoItem>>() {}.type
        return gson.fromJson(data, listType)
    }

    /** Returns a String from a list of [ToDOItem] */
    @TypeConverter
    fun someObjectListToString(someObjects: List<ToDoItem>): String {
        return gson.toJson(someObjects)
    }

}