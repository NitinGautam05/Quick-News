package com.example.quicknews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsRVAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<NewsRVAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView2)
        val desc: TextView = itemView.findViewById(R.id.textView3)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
//        val author: TextView = itemView.findViewById(R.id.newsAuthor)
//        val publishedAt: TextView = itemView.findViewById(R.id.newsPublishedAt)
//        val source: TextView = itemView.findViewById(R.id.url)

        init {
            itemView.setOnClickListener {
                val article = articles[adapterPosition]
                val intent = Intent(context, NewsDetailActivity::class.java).apply {
                    putExtra("title", article.title)
                    putExtra("description", article.description)
                    putExtra("URL", article.url)
                    putExtra("imageURL", article.urlToImage)
                    putExtra("content", article.content)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_rv_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        holder.desc.text = article.description
//        holder.author.text = article.author
//        holder.publishedAt.text = article.publishedAt
//        holder.source.text = article.source.name
        Picasso.get().load(article.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int = articles.size
}
