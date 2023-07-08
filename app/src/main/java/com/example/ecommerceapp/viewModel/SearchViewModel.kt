package com.example.ecommerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.ProductItem
import com.example.ecommerceapp.repository.ProductRepository

class SearchViewModel : ViewModel() {
    private val productRepository: ProductRepository = ProductRepository()

    private var searchProductItemLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    val searchProductsItems: LiveData<List<ProductItem>>
        get() = searchProductItemLiveData

    init {
        searchProductItemLiveData = productRepository.getSearchItemsLiveData()
    }

    fun getSearchItems(categoryId: String) {
        productRepository.fetchSearchItems(categoryId)

    }
}