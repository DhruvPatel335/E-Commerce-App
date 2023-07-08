package com.example.ecommerceapp.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ProductItem(
    val categoryId: String? = "",
    val discount: Int? = 0,
    val id: String? = "",
    val image: String? = "",
    @field:JvmField
    val isFlashSale: Boolean = false,
    @field:JvmField
    val isMegaSale: Boolean = false,
    @field:JvmField
    val isRecommended: Boolean = false,
    val name: String? = "",
    val originalPrice: Int? = 0,

    )
