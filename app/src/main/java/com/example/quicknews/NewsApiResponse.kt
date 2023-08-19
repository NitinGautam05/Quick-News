package com.example.quicknews

data class NewsApiResponse(
    val totalResults: Int,
    val articles: List<Article>
)
