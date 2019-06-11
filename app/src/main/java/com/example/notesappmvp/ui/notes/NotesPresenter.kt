package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.repository.NotesRepository

class NotesPresenter(val notesRepository: NotesRepository,val view: NotesContract.View):NotesContract.Presenter{


    override fun loadNotes() {
        // loads notes from repo,so we need reference
        view.showNotes(notesRepository.getNotes())
    }

    override fun addNote() {

    }

    override fun editNote(id: Long) {

    }


}