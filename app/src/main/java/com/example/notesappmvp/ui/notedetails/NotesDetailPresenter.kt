package com.example.notesappmvp.ui.notedetails

import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository

class NotesDetailPresenter(val notesRepository: NotesRepository, val view: NotesDetailsContract.View):NotesDetailsContract.Presenter{


    override fun addNote(note: Note) {
        notesRepository.addNote(note)
        view.refreshNoteList()

    }

    override fun editNote() {

    }


}