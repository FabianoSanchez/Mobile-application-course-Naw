package com.example.notepad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notepad.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note LIMIT 1")
     fun getNotes(): LiveData<Note>

    @Insert
    suspend fun insertNote(note:Note)

    @Delete
    suspend fun updateNote(note:Note)

}