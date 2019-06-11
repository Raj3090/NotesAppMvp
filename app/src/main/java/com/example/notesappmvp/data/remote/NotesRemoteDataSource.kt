package com.example.notesappmvp.data.remote

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

class NotesRemoteDataSource:NotesDataSource {

    override fun addNote(note: Note) {

    }

    override fun getNotes() :List<Note>{
        val arrayList = ArrayList<Note>()//Creating an empty arraylist
        arrayList.add(Note("first Note","THis is my first Note"))//Adding object in arraylist
        arrayList.add(Note("Second Note","THis is my Second Note"))//Adding object in arraylist
        return arrayList
    }

}