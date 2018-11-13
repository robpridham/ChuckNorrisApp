package com.robpridham.chucknorrisapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.robpridham.chucknorrisapp.data.JokeService
import com.robpridham.chucknorrisapp.data.SingleJoke
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Result

class TextInputViewModel(private val jokeService: JokeService): ViewModel() {

    companion object {
        private const val SEPARATOR_CHAR = ' '
    }

    var enteredName: String? = null

    data class FirstNameLastName(val firstName: String, val lastName: String?)

    private fun getFirstNameLastName(): FirstNameLastName? {
        if (enteredName.isNullOrBlank()) {
            return null
        }
        enteredName?.let {
            val tokens = it.split(SEPARATOR_CHAR, limit = 2)
            if (tokens.size in 1..2 ) {
                return FirstNameLastName(tokens.get(0), tokens.getOrNull(1))
            }
        }
        return null
    }

    fun requestRandomJokeWithCharacter(onResult: (Result<SingleJoke, Error>)->Unit) {
        val firstNameLastName = getFirstNameLastName()
        if (firstNameLastName == null) {
            onResult(Failure(Error("No name entered")))
        } else {
            with (firstNameLastName) {
                jokeService.getRandomJokeWithCharacter(
                    firstName,
                    lastName,
                    onResult
                )
            }
        }
    }
}