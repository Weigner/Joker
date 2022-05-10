package com.weigner.joker.data

import com.weigner.joker.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)

    fun onError(response: String)

    fun onComplete()
}