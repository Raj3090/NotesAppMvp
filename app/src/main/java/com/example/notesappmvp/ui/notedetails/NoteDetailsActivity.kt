package com.example.notesappmvp.ui.notedetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Injection
import com.example.notesappmvp.R
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import kotlinx.android.synthetic.main.content_detail_note.*

class NoteDetailsActivity : AppCompatActivity(),NotesDetailsContract.View {



    lateinit var presenter: NotesDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)
        setSupportActionBar(toolbar)

        val noteId = intent.getStringExtra(NOTE_ID)

        //use injection
        presenter= NotesDetailPresenter(noteId,Injection.provideNotesRepository(this),this)
        fab.setOnClickListener { view ->
            //call mPresenter method ,but before that init
            presenter.editNote()
        }

        presenter.start()

    }

    override fun showDescription(description: String) {
        descriptionTv.text=description
    }

    override fun showTitle(title: String) {
        titleTv.text=title
    }

    override fun showEditNoteUi(noteId: String) {
        val intent= Intent(this,AddEditNoteActivity::class.java)
        intent.putExtra(NoteDetailsActivity.NOTE_ID,noteId)
        startActivityForResult(intent,REQUEST_EDIT_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onResult(requestCode,resultCode)
    }


    override fun showNotesList(requestCode: Int, resultCode: Int) {
        if(requestCode== REQUEST_EDIT_TASK&&resultCode== Activity.RESULT_OK){
            finish()
        }
    }


    companion object{
        val REQUEST_EDIT_TASK = 100
        val NOTE_ID = "detail_note_id"
    }

}
