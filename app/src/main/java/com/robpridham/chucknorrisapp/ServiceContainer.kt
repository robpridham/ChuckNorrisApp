package com.robpridham.chucknorrisapp

import android.os.Handler
import com.robpridham.chucknorrisapp.data.ChuckNorrisJokeService
import com.robpridham.chucknorrisapp.json.JacksonParser
import com.robpridham.chucknorrisapp.network.OkHttpNetworkService
import okhttp3.OkHttpClient

class ServiceContainer(handler: Handler) {

    private val networkService = OkHttpNetworkService(OkHttpClient(), handler)
    val jokeService =  ChuckNorrisJokeService(networkService, JacksonParser())
}