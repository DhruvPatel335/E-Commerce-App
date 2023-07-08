package com.example.ecommerceapp.model

data class ProductDetails(
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
    val starRating: Float = 0f,
    val size: Int = 0,
    val color: String = "",
    val shownSpecification: String = "",
    val styleSpecification: String = "",
    val specificationDescription: String = ""
)
