package com.example.notesappmvp.data.repository

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

class NotesRepository private constructor(notesRemoteDataSource: NotesDataSource,
                                          notesLocalDataSource: NotesDataSource):NotesDataSource{



    override fun updateNote(note: Note) {
        mNotesLocalDataSource.updateNote(note)
    }


    private var mNotesLocalDataSource:NotesDataSource
    private  var mNotesRemoteDataSource:NotesDataSource

    init {
        mNotesRemoteDataSource = checkNotNull(notesRemoteDataSource)
        mNotesLocalDataSource = checkNotNull(notesLocalDataSource)
    }

    override fun getNotes(noteCallBack: NotesDataSource.LoadNotesCallback) {

        if(mCachedTasks!=null&&!mCacheIsDirty){
            noteCallBack.onNotesLoaded(ArrayList(mCachedTasks!!.values))
        }

        if(mCacheIsDirty){
            getNoteFromRemoteSource(noteCallBack)
        }else{

            return mNotesLocalDataSource.getNotes(object : NotesDataSource.LoadNotesCallback{

                override fun onNotesLoaded(notes: List<Note>) {
                    noteCallBack.onNotesLoaded(notes)
                }

                override fun onDataNotAvailable() {
                    getNoteFromRemoteSource(noteCallBack)
                }

            })
        }

    }

    private fun getNoteFromRemoteSource(noteCallBack: NotesDataSource.LoadNotesCallback) {
        mNotesRemoteDataSource.getNotes(object :NotesDataSource.LoadNotesCallback {

            override fun onNotesLoaded(notes: List<Note>) {
                noteCallBack.onNotesLoaded(notes)
            }

            override fun onDataNotAvailable() {

            }

        })

    }

    override fun addNote(note: Note) {
        checkNotNull(note)
        mNotesLocalDataSource.addNote(note)
        mNotesRemoteDataSource.addNote(note)

        if(mCachedTasks==null){
            mCachedTasks= hashMapOf()
        }
        mCachedTasks?.put(note.mId,note)

    }

    override fun getNoteById(id: String, noteCallBack: NotesDataSource.GetNoteCallback) {
        mNotesLocalDataSource.getNoteById(id,object :NotesDataSource.GetNoteCallback{
            override fun onNoteLoaded(note: Note) {
                noteCallBack.onNoteLoaded(note)
            }

            override fun onDataNotAvailable() {

            }

        })
    }

    override fun clearCompletedNotes() {
        mNotesRemoteDataSource.clearCompletedNotes()
        mNotesLocalDataSource.clearCompletedNotes()
    }


    internal var mCacheIsDirty = false

    internal var mCachedTasks: MutableMap<String, Note>? = null

    companion object {

        private var INSTANCE: NotesRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         *
         * @param tasksRemoteDataSource the backend data source
         * @param tasksLocalDataSource  the device storage data source
         * @return the [TasksRepository] instance
         */
        fun getInstance(
            tasksRemoteDataSource: NotesDataSource,
            tasksLocalDataSource: NotesDataSource
        ): NotesRepository =
            INSTANCE ?: synchronized(this){
                INSTANCE ?:NotesRepository(tasksRemoteDataSource, tasksLocalDataSource)
                    .also { INSTANCE = it }
            }

        /**
         * Used to force [.getInstance] to create a new instance
         * next time it's called.
         */
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}