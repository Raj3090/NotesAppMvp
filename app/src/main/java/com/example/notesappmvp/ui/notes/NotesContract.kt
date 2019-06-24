package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesContract{

    interface View {
        fun showNotes(notes:List<Note> )
        fun updateNotesList()
        fun showAddNote()
        fun showLoading(showIndicator: Boolean)
        fun showNoteDetailsUi(noteId: String)
        fun showLoadingTaskError()
    }

    interface Presenter{
        fun start()
        fun addNewNote()
        fun loadNotes(forceUpdate:Boolean)
        fun onResult(requestCode:Int,resultCode:Int)
        fun openNoteDetails(clickedNote: Note)
        fun completeNote(completedNote: Note)
        fun activateNote(activatedNote: Note)

         fun setFiltering(requestType: NotesFilterType)

         fun getFiltering(): NotesFilterType
    }
}