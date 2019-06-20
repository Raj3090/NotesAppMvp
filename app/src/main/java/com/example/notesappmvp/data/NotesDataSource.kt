package com.example.notesappmvp.data

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesDataSource {

    //load note list call back
    interface LoadNotesCallback {

        fun onNotesLoaded(notes: List<Note>)

        fun onDataNotAvailable()
    }

    //load single task callback
    interface GetNoteCallback {

        fun onNoteLoaded(note: Note)

        fun onDataNotAvailable()
    }

    fun getNotes(noteCallBack: LoadNotesCallback)

    fun addNote(note: Note)

    fun getNoteById(id:String,noteCallBack: GetNoteCallback)

}