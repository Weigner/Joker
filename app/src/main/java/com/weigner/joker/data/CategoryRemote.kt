package com.weigner.joker.data


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemote {

    fun findAllCategories(callback: ListCategoryCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories(HTTPClient.API_KEY)
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    } else {
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError((t.message ?: "Erro inesperado"))
                    callback.onComplete()
                }

            })
    }
}