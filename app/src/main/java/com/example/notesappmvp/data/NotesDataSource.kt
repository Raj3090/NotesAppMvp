package com.example.notesappmvp.data

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesDataSource {

    //load note list call back
    interface LoadTasksCallback {

        fun onTasksLoaded(notes: List<Note>)

        fun onDataNotAvailable()
    }

    //load single task callback
    interface GetTaskCallback {

        fun onTaskLoaded(note: Note)

        fun onDataNotAvailable()
    }

    fun getNotes(noteCallBack: LoadTasksCallback)

    fun addNote(note: Note)

}