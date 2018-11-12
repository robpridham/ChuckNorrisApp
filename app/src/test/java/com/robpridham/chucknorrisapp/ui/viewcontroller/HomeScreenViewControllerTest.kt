package com.robpridham.chucknorrisapp.ui.viewcontroller

import com.nhaarman.mockitokotlin2.*
import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.data.SingleJoke
import com.robpridham.chucknorrisapp.ui.view.HomeScreenView
import com.robpridham.chucknorrisapp.ui.viewmodel.HomeScreenViewModel
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Result
import com.robpridham.chucknorrisapp.util.Success
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeScreenViewControllerTest {

    @Mock private lateinit var mockHomeView: HomeScreenView
    @Mock private lateinit var mockViewModel: HomeScreenViewModel
    @Mock private lateinit var mockDialogCreationCallback: (Joke)->Unit

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `on click of random joke produces fetch request to view model`() {
        HomeScreenViewController(mockViewModel, mockHomeView, mockDialogCreationCallback)
        verify(mockViewModel, never()).requestRandomJoke(any())

        val captor = argumentCaptor<()->Unit>()
        verify(mockHomeView).setOnRandomJokeButtonPressed(captor.capture())

        captor.firstValue.invoke()
        verify(mockViewModel).requestRandomJoke(any())
    }

    @Test
    fun `success of VM fetch request produces dialog callback`() {
        HomeScreenViewController(mockViewModel, mockHomeView, mockDialogCreationCallback)
        val viewClickCaptor = argumentCaptor<()->Unit>()
        verify(mockHomeView).setOnRandomJokeButtonPressed(viewClickCaptor.capture())

        viewClickCaptor.firstValue.invoke()

        val singleJoke = mock<SingleJoke>()
        val innerJoke = mock<Joke>()
        whenever(singleJoke.value).thenReturn(innerJoke)

        val viewModelCallbackCaptor = argumentCaptor<(Result<SingleJoke, Error>)->Unit>()
        verify(mockViewModel).requestRandomJoke(viewModelCallbackCaptor.capture())
        viewModelCallbackCaptor.firstValue.invoke(Success(singleJoke))

        verify(mockDialogCreationCallback).invoke(innerJoke)
    }

    @Test
    fun `failure of VM fetch request produces no dialog callback`() {
        HomeScreenViewController(mockViewModel, mockHomeView, mockDialogCreationCallback)
        val viewClickCaptor = argumentCaptor<()->Unit>()
        verify(mockHomeView).setOnRandomJokeButtonPressed(viewClickCaptor.capture())

        viewClickCaptor.firstValue.invoke()

        val viewModelCallbackCaptor = argumentCaptor<(Result<SingleJoke, Error>)->Unit>()
        verify(mockViewModel).requestRandomJoke(viewModelCallbackCaptor.capture())
        viewModelCallbackCaptor.firstValue.invoke(Failure(Error()))

        verify(mockDialogCreationCallback, never()).invoke(any())
    }

    //TODO: test button is disabled whilst request is active

    //TODO: test button is re-enabled once request succeeds or fails
}