package com.example.notesappmvp.ui.notes

import android.app.Activity
import android.widget.Switch
import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity
import java.util.ArrayList

class NotesPresenter(val notesRepository: NotesRepository,
                     val view: NotesContract.View)
    :NotesContract.Presenter{


    var onFirstLoad=true

    var notesFilterType:NotesFilterType=NotesFilterType.ALL_TASKS

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
                val notesToShow = ArrayList<Note>()
                // We filter the notes based on the requestType
                for (note in notes) {
                    when (notesFilterType) {
                        NotesFilterType.ALL_TASKS -> notesToShow.add(note)
                        NotesFilterType.ACTIVE_TASKS -> if (!note.mCompleted) {
                            notesToShow.add(note)
                        }
                        NotesFilterType.COMPLETED_TASKS -> if (note.mCompleted) {
                            notesToShow.add(note)
                        }
                        else -> notesToShow.add(note)
                    }
                }

                if(showUiLoading)
                    view.showLoading(false)

                showNotes(notesToShow)

            }

            override fun onDataNotAvailable() {
                view.showLoading(false)
                view.showLoadingTaskError()
            }
        }
        )

    }


    private fun showNotes(notes: List<Note>){

        if(notes.isEmpty()){
            processEmptyNotes()
        }else{

        }
        setNotesLabel()
        view.showNotes(notes)
    }

    private fun setNotesLabel() {
        when(notesFilterType){

            NotesFilterType.ACTIVE_TASKS->{

            }

            NotesFilterType.ALL_TASKS->{

            }

            NotesFilterType.COMPLETED_TASKS->{

            }

        }
    }

    private fun processEmptyNotes() {
        when(notesFilterType){

            NotesFilterType.ACTIVE_TASKS->{

            }

            NotesFilterType.ALL_TASKS->{

            }

            NotesFilterType.COMPLETED_TASKS->{

            }

        }
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
            loadNotes(false)
        }
    }

    override fun setFiltering(requestType: NotesFilterType) {
        notesFilterType=requestType
    }

    override fun getFiltering(): NotesFilterType =notesFilterType



}