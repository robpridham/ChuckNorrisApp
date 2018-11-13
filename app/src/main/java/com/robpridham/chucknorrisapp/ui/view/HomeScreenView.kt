package com.robpridham.chucknorrisapp.ui.view

import android.view.View
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeScreenView(private val view: View) {
    fun setRandomJokeButtonEnabled(enabled: Boolean) {
        view.btn_random_joke.isEnabled = enabled
    }

    fun setOnRandomJokeButtonPressed(onPress: ()->Unit) {
        view.btn_random_joke.setOnClickListener {
            onPress()
        }
    }

    fun setOnTextInputButtonPressed(onPress: ()->Unit) {
        view.btn_text_input.setOnClickListener {
            onPress()
        }
    }
}

