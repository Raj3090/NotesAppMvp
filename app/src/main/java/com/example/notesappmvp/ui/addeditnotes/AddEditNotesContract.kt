package com.example.notesappmvp.ui.addeditnotes

import com.example.notesappmvp.data.local.db.entity.Note

interface AddEditNotesContract {

    interface View {
       fun refreshNoteList()
        fun showDescription(description: String)
        fun showTitle(title: String)
    }

    interface Presenter{
        fun addNote(note: Note)
        fun editNote()
        fun start()
    }

}