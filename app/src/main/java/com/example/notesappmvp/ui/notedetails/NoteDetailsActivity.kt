package com.example.notesappmvp.ui.notedetails

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Injection
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import kotlinx.android.synthetic.main.content_add_edit_note.*

class NoteDetailsActivity : AppCompatActivity(),NotesDetailsContract.View {


    lateinit var presenter: NotesDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)
        setSupportActionBar(toolbar)

        //use injection
        presenter= NotesDetailPresenter(Injection.provideNotesRepository(this),this)
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
        val TASK_DETAILS = "detail_task"
    }

}
