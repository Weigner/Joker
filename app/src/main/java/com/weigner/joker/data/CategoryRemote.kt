package com.weigner.joker.data

import android.os.Handler
import android.os.Looper

class CategoryRemote {

    fun findAllCategories(callback: ListCategoryCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4",
                "Categoria 5",
                "Categoria 6"
            )

            callback.onSuccess(response)

            //callback.onError("Falha")

            callback.onComplete()
        }, 2000)
    }
}