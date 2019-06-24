package com.example.notesappmvp.ui.notes

import android.app.Activity
import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity

class NotesPresenter(val notesRepository: NotesRepository,
                     val view: NotesContract.View)
    :NotesContract.Presenter{

    var onFirstLoad=true

    override fun openNoteDetails(clickedNote: Note) {
        view.showNoteDetailsUi(noteId = clickedNote.mId)
    }

    override fun completeNote(completedNote: Note) {

    }

    override fun activateNote(activatedNote: Note) {

    }


    override fun start() {
        // loads notes from repo,so we need reference
        loadNotes(false)
    }

    fun loadNotes(forceUpdate:Boolean,showUiLoading:Boolean){

        if(showUiLoading){
            //show loading indicator
            view.showLoading(true)
        }

        if(forceUpdate){
            //invalidate cache
        }

        notesRepository.getNotes(object:NotesDataSource.LoadNotesCallback{

            override fun onNotesLoaded(notes: List<Note>) {
                view.showNotes(notes)
                view.showLoading(false)
            }

            override fun onDataNotAvailable() {
                view.showLoading(false)
            }
        }
        )

    }


    override
    fun loadNotes(forceUpdate:Boolean){

        loadNotes(forceUpdate||onFirstLoad,true)
        onFirstLoad=false
    }

    override fun addNewNote() {
        view.showAddNote()
    }



    override fun onResult(requestCode: Int, resultCode: Int) {
        if(requestCode== AddEditNoteActivity.REQUEST_ADD_TASK&&resultCode== Activity.RESULT_OK){
            start()
        }
    }

}