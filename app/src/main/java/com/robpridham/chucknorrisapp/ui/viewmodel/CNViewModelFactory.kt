package com.robpridham.chucknorrisapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robpridham.chucknorrisapp.ServiceContainer

class CNViewModelFactory(private val serviceContainer: ServiceContainer): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            HomeScreenViewModel::class.java -> return HomeScreenViewModel(serviceContainer.jokeService) as T
            TextInputViewModel::class.java -> return TextInputViewModel(serviceContainer.jokeService) as T
        }
        throw IllegalArgumentException("Unknown view model class ${modelClass.name}")
    }
}