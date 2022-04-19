package com.weigner.joker.presetation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.weigner.joker.data.CategoryRemote
import com.weigner.joker.data.ListCategoryCallback
import com.weigner.joker.model.Category
import com.weigner.joker.view.CategoryItem
import com.weigner.joker.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemote = CategoryRemote()
) : ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {

        /*val categories = mutableListOf<CategoryItem>()

        for (category in response) {
            categories.add(CategoryItem(category))
        }*/

        val start = 190
        val end = 40
        val diff = (start - end) / response.size
        val categories = response.mapIndexed { index, value ->
            val hsv = floatArrayOf(
                start - (diff * index).toFloat(),
                100.0f,
                100.0f

            )
            Category(value, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}