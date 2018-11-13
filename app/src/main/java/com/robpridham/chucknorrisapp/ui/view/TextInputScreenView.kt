package com.robpridham.chucknorrisapp.ui.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.robpridham.chucknorrisapp.util.fadeIn
import com.robpridham.chucknorrisapp.util.fadeOutIfVisible
import kotlinx.android.synthetic.main.fragment_textinput.view.*

class TextInputScreenView(private val view: View) {

    private val textChangedListener = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //do nothing
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            nameChangedCallback?.invoke(s.toString())
        }
    }

    init {
        view.text_input.addTextChangedListener(textChangedListener)
    }

    fun setSearchButtonEnabled(enabled: Boolean) {
        view.btn_search.isEnabled = enabled
    }

    fun setOnSearchButtonPressed(onPress: ()->Unit) {
        view.btn_search.setOnClickListener {
            onPress()
        }
    }

    fun showError(message: String) {
        //TODO: probably don't want to cascade underlying error to user
        view.error_message.text = message
        view.error_message.fadeIn()
    }

    fun hideError() {
        view.error_message.fadeOutIfVisible()
    }

    var nameChangedCallback: ((String)->Unit)? = null
}

