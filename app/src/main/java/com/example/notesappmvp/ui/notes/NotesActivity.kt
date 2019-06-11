package com.example.notesappmvp.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvp.R
import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.local.db.NotesDataBase
import com.example.notesappmvp.data.local.db.NotesLocalDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.remote.NotesRemoteDataSource
import com.example.notesappmvp.data.repository.NotesRepository

class NotesActivity : AppCompatActivity(),NotesContract.View {

    //create presenter
    lateinit var presenter:NotesContract.Presenter
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        recyclerView = findViewById<RecyclerView>(R.id.taskList)

        var notesDataBase:NotesDataBase= NotesDataBase.get(this)

        val appExecutors:AppExecutors=AppExecutors()

        var notesLocalDataSource:NotesLocalDataSource=NotesLocalDataSource(notesDataBase,appExecutors)

        var notesRemoteDataSource:NotesRemoteDataSource= NotesRemoteDataSource()

        var notesRepository:NotesRepository= NotesRepository.getInstance(notesRemoteDataSource,notesLocalDataSource)

        presenter=NotesPresenter(notesRepository,this)

        presenter.loadNotes()
    }


    override fun updateNotesList() {

    }

    override fun showNotes(notes: List<Note>) {
        val layoutManager:LinearLayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=NotesAdapter(notes)

    }




}
