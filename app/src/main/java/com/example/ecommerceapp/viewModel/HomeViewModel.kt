package com.example.ecommerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.ProductItem
import com.example.ecommerceapp.repository.ProductRepository

class HomeViewModel : ViewModel() {
    private val productRepository: ProductRepository = ProductRepository()

    private var recommendedProductItemLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()


    private var flashSaleLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    private var megaSaleLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    val recommendedProductItems: LiveData<List<ProductItem>>
        get() = recommendedProductItemLiveData

    init {
        recommendedProductItemLiveData = productRepository.getRecommendedProductItemsLiveData()
        flashSaleLiveData = productRepository.getFlashSaleProductItemsLiveData()
        megaSaleLiveData = productRepository.getMegaSaleProductItemsLiveData()
    }

    val flashSaleItems: LiveData<List<ProductItem>>
        get() = flashSaleLiveData

    val megaSaleItems: LiveData<List<ProductItem>>
        get() = megaSaleLiveData


    fun fetchRecommendedProducts() {
        productRepository.fetchRecommendedProducts()
    }

    fun fetchFlashSale() {
        productRepository.fetchFlashSale()
    }

    fun fetchMegaSale() {
        productRepository.fetchMegaSale()
    }

}