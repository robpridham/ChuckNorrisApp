package com.robpridham.chucknorrisapp.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.robpridham.chucknorrisapp.data.JokeService
import com.robpridham.chucknorrisapp.data.SingleJoke
import com.robpridham.chucknorrisapp.util.Result

class HomeScreenViewModel(private val jokeService: JokeService): ViewModel() {

    fun requestRandomJoke(onResult: (Result<SingleJoke, Error>)->Unit) {
        jokeService.getRandomJoke(onResult)
    }
}