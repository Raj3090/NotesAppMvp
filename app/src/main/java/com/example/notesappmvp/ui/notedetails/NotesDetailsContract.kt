package com.example.notesappmvp.ui.notedetails

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesDetailsContract {

    interface View {
       fun refreshNoteList()
    }

    interface Presenter{
        fun addNote(note: Note)
        fun editNote()
    }

}