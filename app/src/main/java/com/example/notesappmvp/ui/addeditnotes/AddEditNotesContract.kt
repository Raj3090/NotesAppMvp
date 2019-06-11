package com.example.notesappmvp.ui.addeditnotes

interface AddEditNotesContract {

    interface View {
       fun refreshAdapter()
    }

    interface Presenter{
        fun addNote()
        fun editNote()
    }

}