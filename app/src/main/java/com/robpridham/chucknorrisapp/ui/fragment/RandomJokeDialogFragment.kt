package com.robpridham.chucknorrisapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.robpridham.chucknorrisapp.ChuckNorrisApp
import com.robpridham.chucknorrisapp.R
import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.RandomJokeView
import com.robpridham.chucknorrisapp.ui.viewcontroller.RandomJokeViewController

class RandomJokeDialogFragment : DialogFragment() {

    companion object {
        const val BUNDLE_KEY_JOKE = "joke"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = this.activity?.application as? ChuckNorrisApp
        app?.let {
            val joke = this.arguments?.getParcelable<Joke>(BUNDLE_KEY_JOKE)
            joke?.let {
                val wrappedView = RandomJokeView(view)
                val controller = RandomJokeViewController(wrappedView, joke)
                controller.dismissCallback = {
                    this.dismiss()
                }
            }
        }
    }
}