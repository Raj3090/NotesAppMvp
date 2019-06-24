package com.example.notesappmvp.ui.addeditnotes

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Injection
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.ui.notedetails.NoteDetailsActivity
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import kotlinx.android.synthetic.main.content_add_edit_note.*

class AddEditNoteActivity : AppCompatActivity(),AddEditNotesContract.View {


    lateinit var mPresenter: AddEditNotesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        setSupportActionBar(toolbar)

        val noteId:String? = intent.getStringExtra(NoteDetailsActivity.NOTE_ID)
        //use injection
        mPresenter= AddEditNotesPresenter(noteId,Injection.provideNotesRepository(this),this)
        fab.setOnClickListener { view ->
            //call mPresenter method ,but before that init
            mPresenter.addNote(Note(addTaskTitleTv.text.toString(),addTaskDescriptionTv.text.toString()))
        }
        mPresenter.start()
    }


    override fun refreshNoteList() {
        setResult(Activity.RESULT_OK)
        finish()
    }


    override fun showDescription(description: String) {
        addTaskDescriptionTv.setText(description)
    }

    override fun showTitle(title: String) {
        addTaskTitleTv.setText(title)
    }

    companion object{
        val REQUEST_ADD_TASK = 100
        val EDIT_TASK = "edit_task"
    }

}
