package com.robpridham.chucknorrisapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robpridham.chucknorrisapp.ChuckNorrisApp
import com.robpridham.chucknorrisapp.R
import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.TextInputScreenView
import com.robpridham.chucknorrisapp.ui.viewcontroller.TextInputScreenViewController
import com.robpridham.chucknorrisapp.ui.viewmodel.TextInputViewModel

class TextInputFragment: Fragment() {

    companion object {
        private const val DIALOG_TAG_RANDOM_JOKE = "rj-dialog"
    }

    private lateinit var controller: TextInputScreenViewController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_textinput, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = this.activity?.application as? ChuckNorrisApp
        app?.let {
            val wrappedView = TextInputScreenView(view)
            val viewModel = app.getFragmentScopedViewModel<TextInputViewModel>(this)
            this.controller = TextInputScreenViewController(viewModel, wrappedView) { joke -> showRandomJokeDialogFragment(joke) }
        }
    }

    private fun showRandomJokeDialogFragment(joke: Joke) {
        fragmentManager?.let { fMgr ->
            val ft = fMgr.beginTransaction()
            val prev = fMgr.findFragmentByTag(DIALOG_TAG_RANDOM_JOKE)
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)

            val dialogFragment = RandomJokeDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(RandomJokeDialogFragment.BUNDLE_KEY_JOKE, joke)
            dialogFragment.arguments = bundle
            dialogFragment.show(ft, DIALOG_TAG_RANDOM_JOKE)
        }
    }
}