package com.example.quicknews

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryRVAdapter(private val context: Context, private val categoryList: List<CategoryModel>, private val clickInterface: CategoryClickInterface) :
    RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_rv_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryName.text = category.categoryName

        if (!TextUtils.isEmpty(category.categoryImage)) {
            Picasso.get().load(category.categoryImage).into(holder.categoryImage)
            holder.itemView.setOnClickListener {
                clickInterface.onCategoryClick(category)
            }
        }
    }

    override fun getItemCount(): Int = categoryList.size

    interface CategoryClickInterface {
        fun onCategoryClick(category: CategoryModel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.image)
        val categoryName: TextView = itemView.findViewById(R.id.textView)
    }
}
