package com.example.notesappmvp.ui.notedetails

interface NotesDetailsContract {

    interface View {

        fun showDescription(description: String)
        fun showTitle(title: String)
        fun showEditNoteUi(id: String)
        fun showNotesList(requestCode: Int, resultCode: Int)
    }

    interface Presenter{
        fun editNote()
        fun start()
        fun onResult(requestCode:Int,resultCode:Int)
    }

}