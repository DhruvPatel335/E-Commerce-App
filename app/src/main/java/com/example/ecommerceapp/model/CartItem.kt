package com.example.ecommerceapp.model


data class CartItem(
    val productImage: Int,
    val productName: String,
    val productPrice: String,
    val productQuantity: String,
    val isLiked: Boolean
)