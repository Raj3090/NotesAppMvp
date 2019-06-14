package com.example.notesappmvp.ui.notes

import android.content.Intent
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
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesActivity : AppCompatActivity(),NotesContract.View {

    //create presenter
    lateinit var presenter:NotesContract.Presenter
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        recyclerView = findViewById<RecyclerView>(R.id.taskList)

        //use injection
        val notesDataBase:NotesDataBase= NotesDataBase.get(this)
        val appExecutors:AppExecutors=AppExecutors()
        val notesLocalDataSource:NotesLocalDataSource=NotesLocalDataSource(notesDataBase,appExecutors)
        val notesRemoteDataSource:NotesRemoteDataSource= NotesRemoteDataSource()
        val notesRepository:NotesRepository= NotesRepository.getInstance(notesRemoteDataSource,notesLocalDataSource)
        presenter=NotesPresenter(notesRepository,this)
        presenter.loadNotes()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
              presenter.addNewNote()
        }
    }


    override fun updateNotesList() {

    }

    override fun showNotes(notes: List<Note>) {
        val layoutManager:LinearLayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=NotesAdapter(notes)

    }

    override fun showAddNote() {
        val intent=Intent(this,AddEditNoteActivity::class.java)
        startActivityForResult(intent,AddEditNoteActivity.REQUEST_ADD_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onResult(requestCode,resultCode)
    }




}
