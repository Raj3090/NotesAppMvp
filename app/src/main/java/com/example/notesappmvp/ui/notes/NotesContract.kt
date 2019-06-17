package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesContract{

    interface View {
        fun showNotes(notes:List<Note> )
        fun updateNotesList()
        fun showAddNote()
    }

    interface Presenter{
        fun loadNotes()
        fun addNewNote()
        fun editNote(id: Long)
        fun onResult(requestCode:Int,resultCode:Int)
        fun openNoteDetails(clickedNote: Note)
        fun completeNote(completedNote: Note)
        fun activateNote(activatedNote: Note)
    }
}