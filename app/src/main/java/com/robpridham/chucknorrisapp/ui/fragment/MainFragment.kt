package com.robpridham.chucknorrisapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robpridham.chucknorrisapp.ChuckNorrisApp
import com.robpridham.chucknorrisapp.R
import com.robpridham.chucknorrisapp.data.Joke
import com.robpridham.chucknorrisapp.ui.view.HomeScreenView
import com.robpridham.chucknorrisapp.ui.viewcontroller.HomeScreenViewController
import com.robpridham.chucknorrisapp.ui.viewmodel.HomeScreenViewModel

class MainFragment: Fragment() {

    private lateinit var controller: HomeScreenViewController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = this.activity?.application as? ChuckNorrisApp
        app?.let {
            val wrappedView = HomeScreenView(view)
            val viewModel = app.viewModelFactory.create(HomeScreenViewModel::class.java)
            this.controller = HomeScreenViewController(viewModel, wrappedView) { joke -> showRandomJokeDialogFragment(joke) }
        }
    }

    private fun showRandomJokeDialogFragment(joke: Joke) {
        fragmentManager?.let { fMgr ->
            val ft = fMgr.beginTransaction()
            val prev = fMgr.findFragmentByTag("rj-dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)

            val dialogFragment = RandomJokeDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(RandomJokeDialogFragment.BUNDLE_KEY_JOKE, joke)
            dialogFragment.arguments = bundle
            dialogFragment.show(ft, "rj-dialog")
        }
    }
}