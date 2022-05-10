package com.weigner.joker.presetation

import com.weigner.joker.data.JokeCallback
import com.weigner.joker.data.JokeDayCallback
import com.weigner.joker.data.JokeDayRemote
import com.weigner.joker.model.Joke
import com.weigner.joker.view.JokeDayFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemote = JokeDayRemote()
) : JokeDayCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
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
