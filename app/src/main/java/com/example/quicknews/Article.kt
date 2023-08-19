package com.example.quicknews

//  https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=311a955314bc4fe88d354f92750a03f7

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content : String
    )
