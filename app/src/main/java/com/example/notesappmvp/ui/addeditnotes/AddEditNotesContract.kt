package com.example.notesappmvp.ui.addeditnotes

import com.example.notesappmvp.data.local.db.entity.Note

interface AddEditNotesContract {

    interface View {
       fun refreshNoteList()
    }

    interface Presenter{
        fun addNote(note: Note)
        fun editNote()
    }

}