package com.example

import android.content.Context
import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.local.db.NotesDataBase
import com.example.notesappmvp.data.local.db.NotesLocalDataSource
import com.example.notesappmvp.data.remote.NotesRemoteDataSource
import com.example.notesappmvp.data.repository.NotesRepository


object Injection {

        fun provideNotesRepository(context: Context): NotesRepository {
            checkNotNull(context)
            val database = NotesDataBase.get(context)
            return NotesRepository.getInstance(
                NotesRemoteDataSource(),
                NotesLocalDataSource(
                    database,AppExecutors()
                )
            )
        }
    }

