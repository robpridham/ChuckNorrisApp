package com.robpridham.chucknorrisapp

import android.app.Application
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.robpridham.chucknorrisapp.ui.viewmodel.CNViewModelFactory

class ChuckNorrisApp: Application() {

    private val serviceContainer = ServiceContainer(Handler())

    private val viewModelFactory = CNViewModelFactory(serviceContainer)

//    inline fun <reified T: ViewModel> getActivityScopedViewModel(activity: FragmentActivity): T {
//        val getViewModelProviderForActivity = { viewModelFactory: CNViewModelFactory ->
//            ViewModelProviders.of(activity, viewModelFactory)
//        }
//        return getViewModel(T::class.java, getViewModelProviderForActivity)
//    }

    inline fun <reified T : ViewModel> getFragmentScopedViewModel(fragment: Fragment): T {
        val getViewModelProviderForFragment = { viewModelFactory: CNViewModelFactory ->
            ViewModelProviders.of(fragment, viewModelFactory)
        }
        return getViewModel(T::class.java, getViewModelProviderForFragment)
    }

    fun <T: ViewModel> getViewModel(modelClass: Class<T>,
                                    getViewModelProvider: (CNViewModelFactory) -> ViewModelProvider): T {
        val viewModelFactory = viewModelFactory
        val viewModelProvider = getViewModelProvider(viewModelFactory)
        val viewModel = viewModelProvider.get(modelClass)
        return viewModel
    }
}