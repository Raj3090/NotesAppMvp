package com.example.notesappmvp.data.local.db

import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

class NotesLocalDataSource(private val dataBase: NotesDataBase,
                           private val appExecutors: AppExecutors) :NotesDataSource{

    override fun updateNote(note: Note) {
        val updateRunnable = Runnable {
            dataBase.notesDao().updateNote(note)
        }
        appExecutors.diskIO().execute(updateRunnable)
    }


    override fun getNoteById(id: String, noteCallBack: NotesDataSource.GetNoteCallback) {
        val noteByIdRunnable = Runnable {
            val note=dataBase.notesDao().getNoteById(id)

            appExecutors.mainThread().execute(Runnable {
                noteCallBack.onNoteLoaded(note)
            })
        }
        appExecutors.diskIO().execute(noteByIdRunnable)
    }


    override fun addNote(note: Note) {
        val saveRunnable = Runnable {
            dataBase.notesDao().insertNote(note)
        }
        appExecutors.diskIO().execute(saveRunnable)
    }

    override fun getNotes(noteCallBack: NotesDataSource.LoadNotesCallback) {

        val getRunnable = Runnable {
            val notes = dataBase.notesDao().getNotes()
            appExecutors.mainThread().execute(Runnable {
                noteCallBack.onNotesLoaded(notes)
            })
        }
        appExecutors.diskIO().execute(getRunnable)

    }

}