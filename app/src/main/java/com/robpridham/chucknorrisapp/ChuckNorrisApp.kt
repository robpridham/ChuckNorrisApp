package com.robpridham.chucknorrisapp

import android.app.Application
import android.os.Handler
import com.robpridham.chucknorrisapp.ui.viewmodel.CNViewModelProviderFactory

class ChuckNorrisApp: Application() {

    private val serviceContainer = ServiceContainer(Handler())

    val viewModelFactory = CNViewModelProviderFactory(serviceContainer)
}