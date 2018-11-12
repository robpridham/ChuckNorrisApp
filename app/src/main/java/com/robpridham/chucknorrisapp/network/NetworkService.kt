package com.robpridham.chucknorrisapp.network

import android.os.Handler
import com.robpridham.chucknorrisapp.util.Failure
import com.robpridham.chucknorrisapp.util.Result
import com.robpridham.chucknorrisapp.util.Success
import okhttp3.*
import java.io.IOException

interface NetworkService {
    fun getResponse(url: String, onResult: (Result<String, Error>)->Unit)
}

class OkHttpNetworkService(private val okHttpClient: OkHttpClient, private val handler: Handler): NetworkService {

    override fun getResponse(url: String, onResult: (Result<String, Error>)->Unit) {
        val request = Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    onResult(Failure(Error(e.message)))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                handler.post {
                    response.body()?.let {
                        onResult(Success(it.string()))
                    } ?: onResult(Failure(Error("Empty response")))
                }
            }
        })
    }
}