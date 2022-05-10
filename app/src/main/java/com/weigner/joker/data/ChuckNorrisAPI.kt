package com.weigner.joker.data

import com.weigner.joker.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey") apiKey: String): Call<List<String>>

    @GET("jokes/random")
    fun findByCategory(@Query("category") categoryName: String, @Query("apiKey") apiKey: String): Call<Joke>

    @GET("jokes/random")
    fun findRandom( @Query("apiKey") apiKey: String): Call<Joke>
}