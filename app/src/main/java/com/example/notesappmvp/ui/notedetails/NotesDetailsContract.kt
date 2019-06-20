package com.example.notesappmvp.ui.notedetails

import android.accounts.AuthenticatorDescription
import com.example.notesappmvp.data.local.db.entity.Note

interface NotesDetailsContract {

    interface View {

        fun showDescription(description: String)
        fun showTitle(title: String)
        fun showEditNoteUi(id: String)
    }

    interface Presenter{
        fun editNote()
        fun start()
    }

}