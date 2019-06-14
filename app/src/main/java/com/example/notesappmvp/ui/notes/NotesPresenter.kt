package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.repository.NotesRepository

class NotesPresenter(val notesRepository: NotesRepository,val view: NotesContract.View):NotesContract.Presenter{



    override fun loadNotes() {
        // loads notes from repo,so we need reference
        view.showNotes(notesRepository.getNotes())
    }

    override fun addNewNote() {
        view.showAddNote()
    }

    override fun editNote(id: Long) {

    }

    override fun onResult(requestCode: Int, resultCode: Int) {

    }

}