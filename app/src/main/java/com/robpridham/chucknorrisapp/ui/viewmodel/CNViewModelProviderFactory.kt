package com.robpridham.chucknorrisapp.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.robpridham.chucknorrisapp.ServiceContainer
import java.lang.IllegalArgumentException

class CNViewModelProviderFactory(private val serviceContainer: ServiceContainer): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            HomeScreenViewModel::class.java -> return HomeScreenViewModel(serviceContainer.jokeService) as T
        }
        throw IllegalArgumentException("Unknown view model class ${modelClass.name}")
    }
}