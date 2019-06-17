package com.example.notesappmvp.ui.notes

import android.app.Activity
import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity

class NotesPresenter(val notesRepository: NotesRepository,
                     val view: NotesContract.View)
    :NotesContract.Presenter{


    override fun openNoteDetails(clickedNote: Note) {

    }

    override fun completeNote(completedNote: Note) {
    }

    override fun activateNote(activatedNote: Note) {

    }


    override fun loadNotes() {
        // loads notes from repo,so we need reference
        notesRepository.getNotes(object:NotesDataSource.LoadTasksCallback{

            override fun onTasksLoaded(notes: List<Note>) {
                view.showNotes(notes)
            }

            override fun onDataNotAvailable() {

            }
        }
        )


    }

    override fun addNewNote() {
        view.showAddNote()
    }

    override fun editNote(id: Long) {

    }

    override fun onResult(requestCode: Int, resultCode: Int) {
        if(requestCode== AddEditNoteActivity.REQUEST_ADD_TASK&&resultCode== Activity.RESULT_OK){
            loadNotes()
        }
    }

}