package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.local.db.entity.Note
import com.example.notesappmvp.data.repository.NotesRepository
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NotesPresenterTest {


    @Mock
    lateinit var repository: NotesRepository

    @Mock
    lateinit var mView : NotesContract.View

    private lateinit var mPresenter: NotesPresenter

    @Captor
    private lateinit var mLoadTasksCallbackCaptor: ArgumentCaptor<NotesDataSource.LoadNotesCallback>

    @Before
    public fun initPresenter(){

        MockitoAnnotations.initMocks(this)

        mPresenter= NotesPresenter(repository,mView)
        mLoadTasksCallbackCaptor= ArgumentCaptor.forClass(NotesDataSource.LoadNotesCallback::class.java)

        // We start the tasks to 3, with one active and two completed
        TASKS = arrayListOf<Note>(
            Note("Title1", "Description1"),
            Note("Title2", "Description2", true), Note("Title3", "Description3", true)
        )
    }

    @Test
    public fun verifyNotesLoading(){

        mPresenter.start()

        verify(repository).getNotes(capture(mLoadTasksCallbackCaptor))

        mLoadTasksCallbackCaptor.value.onNotesLoaded(TASKS)

        val showTasksArgumentCaptor = argumentCaptor<List<Note>>()

        verify(mView).showNotes(capture(showTasksArgumentCaptor))

        assertTrue(showTasksArgumentCaptor.value.size==3)


    }

    private lateinit var TASKS: List<Note>

}