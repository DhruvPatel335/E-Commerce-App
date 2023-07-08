package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        AuthorViewModel::class.java -> AuthorViewModel()
        HomeViewModel::class.java -> HomeViewModel()
        SearchViewModel::class.java -> SearchViewModel()
        ProductDetailViewModel::class.java -> ProductDetailViewModel()
        else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
    } as T

}