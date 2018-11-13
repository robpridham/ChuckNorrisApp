package com.robpridham.chucknorrisapp.ui.viewmodel

import com.nhaarman.mockitokotlin2.*
import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.data.JokeService
import com.robpridham.chucknorrisapp.data.SingleJoke
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Result
import com.robpridham.chucknorrisapp.util.Success
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TextInputViewModelTest {

    @Mock private lateinit var mockJokeService: JokeService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test single name produces successful request`() {

        val returnedJoke = SingleJoke("type", Joke(101, "A joke"))

        doAnswer {
            val callback = it.arguments[2] as? (Result<SingleJoke, Error>) -> Unit
            callback?.invoke(Success(returnedJoke))
        }.whenever(mockJokeService).getRandomJokeWithCharacter(eq("Harold"), eq(null), any())

        val model = TextInputViewModel(mockJokeService)
        model.enteredName = "Harold"
        var collectedResult: Result<SingleJoke, Error>? = null
        model.requestRandomJokeWithCharacter { result ->
            collectedResult = result
        }

        assertEquals(returnedJoke, (collectedResult as? Success)?.payload)
    }

    @Test
    fun `test double name produces successful request`() {

        val returnedJoke = SingleJoke("type", Joke(102, "A joke"))

        doAnswer {
            val callback = it.arguments[2] as? (Result<SingleJoke, Error>) -> Unit
            callback?.invoke(Success(returnedJoke))
        }.whenever(mockJokeService).getRandomJokeWithCharacter(eq("Harold"), eq("Realperson"), any())

        val model = TextInputViewModel(mockJokeService)
        model.enteredName = "Harold Realperson"
        var collectedResult: Result<SingleJoke, Error>? = null
        model.requestRandomJokeWithCharacter { result ->
            collectedResult = result
        }

        assertEquals(returnedJoke, (collectedResult as? Success)?.payload)
    }

    @Test
    fun `test extended name flows into last name and produces successful request`() {

        val returnedJoke = SingleJoke("type", Joke(102, "A joke"))

        doAnswer {
            val callback = it.arguments[2] as? (Result<SingleJoke, Error>) -> Unit
            callback?.invoke(Success(returnedJoke))
        }.whenever(mockJokeService).getRandomJokeWithCharacter(eq("Harold"), eq("Realperson Longwinded Name"), any())

        val model = TextInputViewModel(mockJokeService)
        model.enteredName = "Harold Realperson Longwinded Name"
        var collectedResult: Result<SingleJoke, Error>? = null
        model.requestRandomJokeWithCharacter { result ->
            collectedResult = result
        }

        assertEquals(returnedJoke, (collectedResult as? Success)?.payload)
    }

    @Test
    fun `test blank name produces failure result without service call`() {
        val model = TextInputViewModel(mockJokeService)
        model.enteredName = "   "
        var collectedResult: Result<SingleJoke, Error>? = null
        model.requestRandomJokeWithCharacter { result ->
            collectedResult = result
        }

        verifyZeroInteractions(mockJokeService)
        assertTrue(collectedResult is Failure)
    }
}