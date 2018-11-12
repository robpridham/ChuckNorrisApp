package com.robpridham.chucknorrisapp.ui.viewcontroller

import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.HomeScreenView
import com.robpridham.chucknorrisapp.ui.viewmodel.HomeScreenViewModel
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Success

class HomeScreenViewController(
    private val homeScreenViewModel: HomeScreenViewModel,
    view: HomeScreenView,
    createRandomJokeDialogCallback: (Joke)->Unit) {

    init {
        view.setOnRandomJokeButtonPressed {
            //TODO: better represent failure case in UI
            view.setRandomJokeButtonEnabled(false)
            homeScreenViewModel.requestRandomJoke { result ->
                view.setRandomJokeButtonEnabled(true)
                when (result) {
                    is Success -> createRandomJokeDialogCallback(result.payload.value)
                    is Failure -> {}
                }
            }
        }
    }
}