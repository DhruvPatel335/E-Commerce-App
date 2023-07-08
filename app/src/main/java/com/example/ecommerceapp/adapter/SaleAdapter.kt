package com.example.ecommerceapp.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.fragments.home.FavoriteProducts
import com.example.ecommerceapp.model.ProductItem

class SaleAdapter(
    private val context: Context,
    private var productList: List<ProductItem>,
    private val itemCount: Int
) :
    RecyclerView.Adapter<SaleAdapter.ViewHolder>() {


    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = productList[position]
        Glide.with(context).load(productItem.image).into(holder.productImage)
        holder.productName.text = productItem.name
        holder.productDiscount.text = productItem.discount.toString() + "% Off"
        holder.productOriginalPrice.text = "$ " + productItem.originalPrice.toString()
        holder.productOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        val discount =
            (productItem.originalPrice!!.toDouble() - productItem.originalPrice.toDouble() * (productItem.discount!!.toDouble() / 100))
        holder.productPrice.text = "$ " + String.format("%.2f", discount)

    }

    override fun getItemCount(): Int {
        return productList.count()
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val productPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val productOriginalPrice: TextView = itemView.findViewById(R.id.tvOriginalProductPrice)
        val productDiscount: TextView = itemView.findViewById(R.id.tvDiscount)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }
}