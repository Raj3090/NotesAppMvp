package com.example.notesappmvp

import android.content.Context
import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.NotesFakeRemoteDataSource
import com.example.notesappmvp.data.local.db.NotesDataBase
import com.example.notesappmvp.data.local.db.NotesLocalDataSource
import com.example.notesappmvp.data.repository.NotesRepository


object Injection {

        fun provideNotesRepository(context: Context): NotesRepository {
            checkNotNull(context)
            val database = NotesDataBase.get(context)
            return NotesRepository.getInstance(
                NotesFakeRemoteDataSource(),
                NotesLocalDataSource(
                    database,AppExecutors()
                )
            )
        }
    }

