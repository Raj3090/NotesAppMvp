package com.example.notesappmvp.data.local.db.dao

import androidx.room.*
import com.example.notesappmvp.data.local.db.entity.Note

@Dao
public interface NotesDao {

    @Query("SELECT * from notes")
    fun getNotes():List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM notes WHERE entryid = :noteId")
    fun getNoteById(noteId:String):Note

}