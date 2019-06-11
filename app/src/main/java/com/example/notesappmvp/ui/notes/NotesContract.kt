package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesContract{

    interface View {
        fun showNotes(tasks:List<Note> )
        fun updateNotesList()
    }

    interface Presenter{
        fun loadNotes()
        fun addNote()
        fun editNote(id: Long)
    }
}