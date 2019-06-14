package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesContract{

    interface View {
        fun showNotes(tasks:List<Note> )
        fun updateNotesList()
        fun showAddNote()
    }

    interface Presenter{
        fun loadNotes()
        fun addNewNote()
        fun editNote(id: Long)
        fun onResult(requestCode:Int,resultCode:Int)
    }
}