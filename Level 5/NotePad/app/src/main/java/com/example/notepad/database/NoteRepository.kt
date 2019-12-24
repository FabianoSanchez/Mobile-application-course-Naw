package com.example.notepad.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notepad.model.Note

class NoteRepository(context: Context){

    private var notepadDao : NoteDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        notepadDao = database!!.noteDao()
    }


     fun getNotes(): LiveData<Note> {
         return notepadDao.getNotes()
    }

     suspend fun insertNote(note: Note) {
         return notepadDao.insertNote(note)
    }

     suspend fun updateNote(note: Note) {
         return notepadDao.updateNote(note)
    }

}