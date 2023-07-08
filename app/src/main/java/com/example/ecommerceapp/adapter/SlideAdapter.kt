package com.example.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.SlideItem

class SlideAdapter(
    private val context: Context,
    private val slideItems: List<SlideItem>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<SlideAdapter.ViewHolder>() {
    private var carouselImages: List<SlideItem> = slideItems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(carouselImages[position].image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return carouselImages.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.carouselImage)
    }

}