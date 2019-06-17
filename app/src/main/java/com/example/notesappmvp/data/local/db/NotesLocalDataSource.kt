package com.example.notesappmvp.data.local.db

import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

class NotesLocalDataSource(private val dataBase: NotesDataBase,
                           private val appExecutors: AppExecutors) :NotesDataSource{


    override fun addNote(note: Note) {
        val saveRunnable = Runnable {
            dataBase.notesDao().insertNote(note)
        }
        appExecutors.diskIO().execute(saveRunnable)
    }

    override fun getNotes(noteCallBack: NotesDataSource.LoadTasksCallback) {

        val getRunnable = Runnable {
            noteCallBack.onTasksLoaded(dataBase.notesDao().getNotes())
        }
        appExecutors.diskIO().execute(getRunnable)

    }

}