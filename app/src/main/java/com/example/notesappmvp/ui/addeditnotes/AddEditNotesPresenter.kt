package com.example.notesappmvp.ui.addeditnotes

import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository

class AddEditNotesPresenter(val notesRepository: NotesRepository,val view: AddEditNotesContract.View):AddEditNotesContract.Presenter{


    override fun addNote(note: Note) {
        notesRepository.addNote(note)
        view.refreshNoteList()

    }

    override fun editNote() {

    }


}