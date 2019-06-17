package com.example.notesappmvp.ui.addeditnotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesappmvp.R
import com.example.notesappmvp.common.AppExecutors
import com.example.notesappmvp.data.local.db.NotesDataBase
import com.example.notesappmvp.data.local.db.NotesLocalDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.remote.NotesRemoteDataSource
import com.example.notesappmvp.data.repository.NotesRepository
import com.example.notesappmvp.ui.notes.NotesPresenter

import kotlinx.android.synthetic.main.activity_add_edit_note.*
import kotlinx.android.synthetic.main.content_add_edit_note.*

class AddEditNoteActivity : AppCompatActivity(),AddEditNotesContract.View {


    lateinit var presenter: AddEditNotesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        setSupportActionBar(toolbar)

        //use injection
        val notesDataBase: NotesDataBase = NotesDataBase.get(this)
        val appExecutors: AppExecutors = AppExecutors()
        val notesLocalDataSource: NotesLocalDataSource = NotesLocalDataSource(notesDataBase,appExecutors)
        val notesRemoteDataSource: NotesRemoteDataSource = NotesRemoteDataSource()
        val notesRepository: NotesRepository = NotesRepository.getInstance(notesRemoteDataSource,notesLocalDataSource)
        presenter= AddEditNotesPresenter(notesRepository,this)

        fab.setOnClickListener { view ->
            //call mPresenter method ,but before that init
            presenter.addNote(Note(addTaskTitleTv.text.toString(),addTaskDescriptionTv.text.toString()))
        }
    }


    override fun refreshNoteList() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object{
        val REQUEST_ADD_TASK = 100
    }

}
