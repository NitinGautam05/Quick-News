package com.example.quicknews

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val url = intent.getStringExtra("URL")
        val imagetoURL = intent.getStringExtra("imageURL")
        val data = intent.getStringExtra("content")

        val heading: TextView = findViewById(R.id.title)
        val Subdescription: TextView = findViewById(R.id.description)
        val content: TextView = findViewById(R.id.content)
        val img: ImageView = findViewById(R.id.imageID)
        val but: Button = findViewById(R.id.readNews)

        heading.text = title
        Subdescription.text = description
        content.text = data

        // Load image from URL using a library like Picasso
        Picasso.get().load(imagetoURL).into(img)

        but.setOnClickListener {
            // Launch activity to open the full news article in a web browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

    }
}