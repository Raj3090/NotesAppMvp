package com.example.notesappmvp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappmvp.data.local.db.entity.Note

@Database(entities = arrayOf(Note::class),version = 1)
abstract class NotesDataBase : RoomDatabase(){

    companion object{
        private var instance:NotesDataBase?=null

        fun get(context: Context):NotesDataBase= instance?: synchronized(this){
            instance?: buildDatabase(context).also {instance=it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NotesDataBase::class.java, "Notes.db")
                .build()

    }


}
