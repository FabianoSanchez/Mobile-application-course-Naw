package com.app.development.todolist.database

import androidx.room.TypeConverter
import com.app.development.todolist.model.ToDo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList

class Converters {
    private var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<ToDo> {
            if (data == null) {
                return emptyList()
            }

            val listType = object : TypeToken<List<ToDo>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<ToDo>): String {
            return gson.toJson(someObjects)
        }

}