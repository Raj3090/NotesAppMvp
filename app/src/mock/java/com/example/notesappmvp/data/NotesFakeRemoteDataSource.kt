package com.example.notesappmvp.data

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

class NotesFakeRemoteDataSource:NotesDataSource {

    override fun addNote(note: Note) {

    }

    override fun getNotes(noteCallBack: NotesDataSource.LoadTasksCallback) {

    }

}