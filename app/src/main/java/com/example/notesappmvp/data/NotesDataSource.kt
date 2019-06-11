package com.example.notesappmvp.data

import com.example.notesappmvp.data.local.db.entity.Note

interface NotesDataSource {

    fun getNotes():List<Note>

    fun addNote(note: Note)

}