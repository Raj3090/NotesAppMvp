package com.example.notesappmvp.ui.notes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.Injection
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.ui.addeditnotes.AddEditNoteActivity
import com.example.notesappmvp.ui.notedetails.NoteDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_notes.view.*


class NotesActivity : AppCompatActivity(),NotesContract.View {


    //create mPresenter
    lateinit var mPresenter:NotesContract.Presenter
    lateinit var recyclerView:RecyclerView
    lateinit var swipeContainer:SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setUpUi()
        //use injection
        mPresenter=NotesPresenter(Injection.provideNotesRepository(this),this)
    }

    private fun setUpUi() {
        // Set up the toolbar.
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar

        recyclerView = findViewById<RecyclerView>(R.id.taskList)
        swipeContainer=findViewById<SwipeRefreshLayout>(R.id.swipeContainer)

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            mPresenter.loadNotes(true)
        })
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

        findViewById<FloatingActionButton>(com.example.notesappmvp.R.id.fab).setOnClickListener {
            mPresenter.addNewNote()
        }
    }


    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }


    override fun showLoading(showIndicator: Boolean) {
        swipeContainer.isRefreshing = showIndicator
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
        startActivity(intent)
    }

    override fun showNoteDetailsUi(noteId: String) {
        val intent=Intent(this,NoteDetailsActivity::class.java)
        intent.putExtra(NoteDetailsActivity.NOTE_ID,noteId)
        startActivity(intent)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPresenter.onResult(requestCode,resultCode)
    }

    override fun showLoadingTaskError() {
        showMessage(getString(R.string.loading_tasks_error))
    }

    private fun showMessage(message: String) {
        Snackbar.make(findViewById(R.id.parentLayout), message, Snackbar.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.clear -> {true}
            R.id.refresh ->{
                mPresenter.loadNotes(true)
                true
            }
            R.id.all -> {
                mPresenter.setFiltering(NotesFilterType.ALL_TASKS)
                mPresenter.loadNotes(false)
                true
            }
            R.id.active -> {
                mPresenter.setFiltering(NotesFilterType.ACTIVE_TASKS)
                mPresenter.loadNotes(false)
                true
            }
            R.id.completed -> {
                mPresenter.setFiltering(NotesFilterType.COMPLETED_TASKS)
                mPresenter.loadNotes(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
