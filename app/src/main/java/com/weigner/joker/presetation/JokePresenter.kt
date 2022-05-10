package com.weigner.joker.presetation

import com.weigner.joker.data.CategoryRemote
import com.weigner.joker.data.JokeCallback
import com.weigner.joker.data.JokeRemote
import com.weigner.joker.model.Joke
import com.weigner.joker.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemote = JokeRemote()
) : JokeCallback {

    fun findByCategory(categoryName: String) {
        view.showProgress()
        dataSource.findByCategory(categoryName, this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}

