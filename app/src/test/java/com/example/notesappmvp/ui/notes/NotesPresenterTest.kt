package com.example.notesappmvp.ui.notes

import com.example.notesappmvp.data.NotesDataSource
import com.example.notesappmvp.data.repository.NotesRepository
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
    }

    @Test
    public fun verifyNotesLoading(){

        mPresenter.loadNotes()

        verify(repository).getNotes(capture(mLoadTasksCallbackCaptor))

        mLoadTasksCallbackCaptor.value.onDataNotAvailable()

    }



}