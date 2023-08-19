package com.example.quicknews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL= "https://newsapi.org/"
const val API_KEY = "311a955314bc4fe88d354f92750a03f7"

interface NewsAPI {
    @GET("v2/top-headlines?apiKey=$API_KEY")
     fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String?,
        @Query("page") page: Int
    ): Call<NewsApiResponse>
}

object Retrofitinstance {

    val newsApiService : NewsAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApiService = retrofit.create(NewsAPI::class.java)

    }
}

//https://newsapi.org/v2/top-headlines?apiKey=311a955314bc4fe88d354f92750a03f7&country=in&page=1
