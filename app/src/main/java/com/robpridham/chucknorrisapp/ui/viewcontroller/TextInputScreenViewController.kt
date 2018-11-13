package com.robpridham.chucknorrisapp.ui.viewcontroller

import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.TextInputScreenView
import com.robpridham.chucknorrisapp.ui.viewmodel.TextInputViewModel
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Success

class TextInputScreenViewController(
    private val textInputViewModel: TextInputViewModel,
    private val view: TextInputScreenView,
    createRandomJokeDialogCallback: (Joke)->Unit) {

    init {
        view.setOnSearchButtonPressed {
            view.setSearchButtonEnabled(false)
            textInputViewModel.requestRandomJokeWithCharacter { result ->
                view.setSearchButtonEnabled(true)
                when (result) {
                    is Success -> createRandomJokeDialogCallback(result.payload.value)
                    is Failure -> handleFailure(result.error.message)
                }
            }
        }
        view.nameChangedCallback = {
            textInputViewModel.enteredName = it
            view.hideError()
        }
    }

    private fun handleFailure(message: String?) {
        message?.let {
            view.showError(message)
        }
    }
}