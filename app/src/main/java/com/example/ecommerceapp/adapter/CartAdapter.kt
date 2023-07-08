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
import com.example.ecommerceapp.model.CartItem

class CartAdapter(
    private val context: Context,
    private val cartList: MutableList<CartItem>,
    private val itemCount: Int
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
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
        return CartAdapter.ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartList[position]
        Glide.with(context).load(cartItem.productImage).into(holder.productImage)
        holder.quantity.text = cartItem.productQuantity
        holder.productPrice.text = cartItem.productPrice
        holder.productName.text = cartItem.productName
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val productName = itemView.findViewById<TextView>(R.id.tvProductName)
        val quantity = itemView.findViewById<TextView>(R.id.tvQuantity)
        val productPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val like = itemView.findViewById<ImageView>(R.id.ivLike)
        val trash = itemView.findViewById<ImageView>(R.id.ivTrash)

    }
}