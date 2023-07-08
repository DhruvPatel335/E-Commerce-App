package com.example.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Category

class CategoryAdapter(
    private val context: Context,
    private val categoryList: MutableList<Category>,
    private val itemCount: Int
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onCategoryItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        Glide.with(context).load(category.image).into(holder.categoryImage)
        holder.categoryName.text = category.name
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.ivCategoryImage)
        val categoryName: TextView = itemView.findViewById(R.id.tvCategoryItemName)

        init {
            itemView.setOnClickListener {
                listener.onCategoryItemClick(bindingAdapterPosition)
            }
        }
    }

}