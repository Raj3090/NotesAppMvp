package com.example.notesappmvp.ui.addeditnotes

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesappmvp.R

import kotlinx.android.synthetic.main.activity_add_edit_note.*

class AddEditNoteActivity : AppCompatActivity(),AddEditNotesContract.View {


    lateinit var presenter: AddEditNotesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //call presenter method ,but before that init
            presenter.addNote()
        }
    }

    override fun refreshAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
