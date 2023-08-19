package com.example.quicknews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), CategoryRVAdapter.CategoryClickInterface {
    private lateinit var adapter: NewsRVAdapter
    private lateinit var categoryList: List<CategoryModel>
    private lateinit var categoryAdapter: CategoryRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryList = listOf(
            CategoryModel("Business", "https://images.unsplash.com/photo-1591696205602-2f950c417cb9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDl8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"),
            CategoryModel("Entertainment", "https://images.unsplash.com/photo-1542707067-91f05e1ac1fe?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTczfHxlbnRlcnRhaW5tZW50fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"),
            CategoryModel("Health", "https://images.unsplash.com/photo-1511688878353-3a2f5be94cd7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"),
            CategoryModel("Science", "https://images.unsplash.com/photo-1507668077129-56e32842fceb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8c2NpZW5jZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"),
            CategoryModel("Sports", "https://images.unsplash.com/photo-1518611012118-696072aa579a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHNwb3J0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"),
            CategoryModel("Technology", "https://images.unsplash.com/photo-1568952433726-3896e3881c65?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=600&q=60")
        )

        val horizontalRecyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        categoryAdapter = CategoryRVAdapter(this, categoryList, this)

        horizontalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerView.adapter = categoryAdapter

        getNews(null)
    }

    private fun getNews(categoryName: String?){
        val news = Retrofitinstance.newsApiService.getTopHeadlines("in", categoryName, 1, )

        news.enqueue(object :retrofit2.Callback<NewsApiResponse>{
            override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) {
                val news = response.body()
                if (news!= null){
                    Log.d("newsInformation", news.toString())
                    adapter = NewsRVAdapter(this@MainActivity, news.articles)
                    val newsRV = findViewById<RecyclerView>(R.id.recyclerView2)
                    newsRV.adapter = adapter
                    newsRV.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                Log.d("newsInformation", "Error in fetching news", t)

            }
        })
    }

    override fun onCategoryClick(category: CategoryModel) {
        val categoryName = category.categoryName
        getNews(categoryName)
    }
}
