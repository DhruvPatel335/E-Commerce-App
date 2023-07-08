package com.example.ecommerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.ProductDetails
import com.example.ecommerceapp.repository.ProductRepository

class ProductDetailViewModel : ViewModel() {
    private val productRepository: ProductRepository = ProductRepository()
    private var productDetailsLiveData: MutableLiveData<ProductDetails> =
        MutableLiveData()
    val productDetails: LiveData<ProductDetails>
        get() = productDetailsLiveData

    init {
        productDetailsLiveData = productRepository.getProductDetails()
    }

    fun fetchProductDetails(productId: String) {
        productRepository.fetchProductDetails(productId)
    }
}