package com.example.notesappmvp.ui.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Injection
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesActivity : AppCompatActivity(),NotesContract.View {

    //create mPresenter
    lateinit var mPresenter:NotesContract.Presenter
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        recyclerView = findViewById<RecyclerView>(R.id.taskList)

        //use injection
        mPresenter=NotesPresenter(Injection.provideNotesRepository(this),this)
        mPresenter.loadNotes()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
              mPresenter.addNewNote()
        }
    }


    override fun updateNotesList() {

    }

    override fun showNotes(notes: List<Note>) {
        val layoutManager:LinearLayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=NotesAdapter(notes,mItemListener)
    }

    override fun showAddNote() {
        val intent=Intent(this,AddEditNoteActivity::class.java)
        startActivityForResult(intent,AddEditNoteActivity.REQUEST_ADD_TASK)
    }

    override fun showNoteDetailsUi(noteId: String) {
        val intent=Intent(this,AddEditNoteActivity::class.java)
        intent.putExtra(AddEditNoteActivity.EDIT_TASK,noteId)
        startActivity(intent)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPresenter.onResult(requestCode,resultCode)
    }


    /**
     * Listener for clicks on tasks in the ListView.
     */
    internal var mItemListener: NotesAdapter.NoteItemListener = object : NotesAdapter.NoteItemListener {

        override fun onNoteClick(clickedNote: Note) {
            mPresenter.openNoteDetails(clickedNote)
        }

        override fun onCompleteNoteClick(completedNote: Note) {
            mPresenter.completeNote(completedNote)
        }

        override fun onActivateNoteClick(activatedNote: Note) {
            mPresenter.activateNote(activatedNote)
        }
    }


}
