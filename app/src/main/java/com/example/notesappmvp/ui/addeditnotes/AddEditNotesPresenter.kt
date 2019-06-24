package com.example.notesappmvp.ui.addeditnotes

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository

class AddEditNotesPresenter(var noteId: String?,
                            val notesRepository: NotesRepository,
                            val view: AddEditNotesContract.View)
    :AddEditNotesContract.Presenter{


    override fun addNote(note: Note) {
         if(isNewNote()){
             notesRepository.addNote(note)
         }else{
             note.mId=noteId!!
             notesRepository.updateNote(note)
         }

        view.refreshNoteList()
    }

    override fun editNote() {

    }

    override fun start() {

        if(!isNewNote())
        notesRepository.getNoteById(noteId!!,object : NotesDataSource.GetNoteCallback{

            override fun onNoteLoaded(note: Note) {
                showNote(note)
            }

            override fun onDataNotAvailable() {

            }

        })
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

    }


    private fun isNewNote():Boolean=(noteId==null)


}