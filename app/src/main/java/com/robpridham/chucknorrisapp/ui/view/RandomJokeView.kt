package com.robpridham.chucknorrisapp.ui.view

import android.view.View
import com.robpridham.chucknorrisapp.util.setTextFromHtml
import kotlinx.android.synthetic.main.fragment_random_joke.view.*


class RandomJokeView(private val view: View) {

    fun setJokeText(text: String) {
        view.joke_text.setTextFromHtml(text)
    }

    fun setOnOkButtonPressed(onPress: ()->Unit) {
        view.btn_ok.setOnClickListener {
            onPress()
        }
    }
}

