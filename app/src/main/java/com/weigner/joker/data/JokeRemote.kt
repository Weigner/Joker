package com.weigner.joker.data

import com.weigner.joker.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class JokeRemote {
    fun findByCategory(categoryName: String, callback: JokeCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findByCategory(categoryName, HTTPClient.API_KEY)
            .enqueue(object : Callback<Joke>{
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        callback.onSuccess(joke ?: throw RuntimeException("Joke não encontrada"))
                    } else {
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError((t.message ?: "Erro inesperado"))
                    callback.onComplete()
                }

            })
    }

}