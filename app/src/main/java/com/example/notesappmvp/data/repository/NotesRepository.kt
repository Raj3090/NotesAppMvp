package com.example.notesappmvp.data.repository

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note

public class NotesRepository private constructor(notesRemoteDataSource: NotesDataSource,
                                          notesLocalDataSource: NotesDataSource):NotesDataSource{


    private var mNotesLocalDataSource:NotesDataSource
    private  var mNotesRemoteDataSource:NotesDataSource

    init {
        mNotesRemoteDataSource = checkNotNull(notesRemoteDataSource)
        mNotesLocalDataSource = checkNotNull(notesLocalDataSource)
    }

    override fun getNotes(noteCallBack: NotesDataSource.LoadTasksCallback) {

        if(mCachedTasks!=null&&!mCacheIsDirty){
            noteCallBack.onTasksLoaded(ArrayList(mCachedTasks!!.values))
        }

        if(mCacheIsDirty){
            getNoteFromRemoteSource(noteCallBack)
        }else{

            return mNotesLocalDataSource.getNotes(object : NotesDataSource.LoadTasksCallback{

                override fun onTasksLoaded(notes: List<Note>) {
                    noteCallBack.onTasksLoaded(notes)
                }

                override fun onDataNotAvailable() {
                    getNoteFromRemoteSource(noteCallBack)
                }

            })
        }

    }

    private fun getNoteFromRemoteSource(noteCallBack: NotesDataSource.LoadTasksCallback) {
        mNotesRemoteDataSource.getNotes(object :NotesDataSource.LoadTasksCallback {

            override fun onTasksLoaded(notes: List<Note>) {
                noteCallBack.onTasksLoaded(notes)
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