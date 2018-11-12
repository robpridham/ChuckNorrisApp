package com.robpridham.chucknorrisapp.ui.viewcontroller

import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.RandomJokeView

class RandomJokeViewController(private val view: RandomJokeView, joke: Joke) {

    var dismissCallback: (()->Unit)? = null

    init {
        view.setJokeText(joke.joke)
        view.setOnOkButtonPressed {
            dismissCallback?.invoke()
        }
    }
}