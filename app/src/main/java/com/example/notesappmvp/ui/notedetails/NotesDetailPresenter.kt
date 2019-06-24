package com.example.notesappmvp.ui.notedetails

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository

class NotesDetailPresenter(val noteId:String,
                           val notesRepository: NotesRepository,
                           val view: NotesDetailsContract.View):NotesDetailsContract.Presenter{



    override fun start() {
        notesRepository.getNoteById(noteId,object :NotesDataSource.GetNoteCallback{

            override fun onNoteLoaded(note: Note) {
                showNote(note)
            }

            override fun onDataNotAvailable() {

            }

        })
    }


    override fun editNote() {
         view.showEditNoteUi(noteId)
    }

    private fun showNote(note:Note){

        val mTitle = note.mTitle
        val mDescription = note.mDescription

        if(!mTitle.isEmpty()){
            view.showTitle(mTitle)
        }

        if(!mDescription.isEmpty()){
           view.showDescription(mDescription)
        }


    }


    override fun onResult(requestCode: Int, resultCode: Int) {
        view.showNotesList(requestCode,resultCode)
    }

}