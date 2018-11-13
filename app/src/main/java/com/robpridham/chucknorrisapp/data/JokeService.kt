package com.robpridham.chucknorrisapp.data

import com.robpridham.chucknorrisapp.config.LocalConfig
import com.robpridham.chucknorrisapp.json.JsonParser
import com.robpridham.chucknorrisapp.network.NetworkService
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Result
import com.robpridham.chucknorrisapp.util.Success

interface JokeService {
    fun getRandomJoke(onResult: (Result<SingleJoke, Error>) -> Unit)
    fun getRandomJokeWithCharacter(firstName: String?, lastName: String?, onResult: (Result<SingleJoke, Error>) -> Unit)
}

class ChuckNorrisJokeService(
    private val networkService: NetworkService,
    private val jsonParser: JsonParser): JokeService {

    override fun getRandomJoke(onResult: (Result<SingleJoke, Error>) -> Unit) {
        val url = "${LocalConfig.CN_SERVER_PRIMARY_URL}${LocalConfig.CN_RANDOM_JOKES_ENDPOINT}"

        networkService.getRequest(url) {
            when (it) {
                is Success -> parseSingleJoke(it.payload, onResult)
                is Failure -> onResult(Failure(Error()))
            }
        }
    }

    override fun getRandomJokeWithCharacter(firstName: String?, lastName: String?, onResult: (Result<SingleJoke, Error>) -> Unit) {
        val url = "${LocalConfig.CN_SERVER_PRIMARY_URL}${LocalConfig.CN_RANDOM_JOKES_ENDPOINT}"

        val params = mutableMapOf<String, String>()
        firstName?.let { params[LocalConfig.CN_PARAM_FIRST_NAME] = it }
        lastName?.let { params[LocalConfig.CN_PARAM_LAST_NAME] = it }

        networkService.getRequest(url, params) {
            when (it) {
                is Success -> parseSingleJoke(it.payload, onResult)
                is Failure -> onResult(Failure(Error()))
            }
        }
    }

    private fun parseSingleJoke(jokeJson: String, onResult: (Result<SingleJoke, Error>) ->Unit){
        try {
            val singleJoke = jsonParser.fromJson(jokeJson, SingleJoke::class.java)
            onResult(Success(singleJoke))
        }
        //TODO: tighten exception types, e.g. JsonProcessingException, IllegalArgument
        catch (e: Exception) {
            onResult(Failure(Error(e.message)))
        }
    }
}