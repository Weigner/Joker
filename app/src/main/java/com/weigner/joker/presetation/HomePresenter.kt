package com.weigner.joker.presetation

import android.os.Handler
import android.os.Looper
import com.weigner.joker.model.Category
import com.weigner.joker.view.CategoryItem
import com.weigner.joker.view.HomeFragment

class HomePresenter(private val view: HomeFragment) {

    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    private fun onSuccess(response: List<String>) {

        /*val categories = mutableListOf<CategoryItem>()

        for (category in response) {
            categories.add(CategoryItem(category))
        }*/
        val categories = response.map { Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    private fun onError(message: String) {
        view.showFailure(message)
    }

    private fun onComplete() {
        view.hideProgress()
    }

    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4",
                "Categoria 5",
                "Categoria 6"
            )

            onSuccess(response)

            //onError("Falha")

            onComplete()
        }, 2000)
    }
}