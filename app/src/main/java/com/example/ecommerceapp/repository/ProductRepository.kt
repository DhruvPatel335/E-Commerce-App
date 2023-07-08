package com.example.ecommerceapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.constants.PRODUCTS
import com.example.ecommerceapp.model.ProductDetails
import com.example.ecommerceapp.model.ProductItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductRepository {
    private val dbAuthors =
        Firebase.database("https://e-commerceapp-7c242-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference(PRODUCTS)

    private val recommendedProductItemLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    private val flashSaleLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    private val megaSaleLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()


    private val searchProductItemLiveData: MutableLiveData<List<ProductItem>> =
        MutableLiveData()

    private val productDetails: MutableLiveData<ProductDetails> =
        MutableLiveData()

    fun getRecommendedProductItemsLiveData(): MutableLiveData<List<ProductItem>> {
        return recommendedProductItemLiveData
    }

    fun getFlashSaleProductItemsLiveData(): MutableLiveData<List<ProductItem>> {
        return flashSaleLiveData
    }

    fun getMegaSaleProductItemsLiveData(): MutableLiveData<List<ProductItem>> {
        return megaSaleLiveData
    }

    fun getSearchItemsLiveData(): MutableLiveData<List<ProductItem>> {
        return searchProductItemLiveData
    }

    fun getProductDetails(): MutableLiveData<ProductDetails> {
        return productDetails
    }

    fun fetchRecommendedProducts() {
        dbAuthors.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var products = mutableListOf<ProductItem>()
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(ProductItem::class.java)
                        if (product?.isRecommended == true) {
                            product.let {
                                products.add(it)
                            }
                        }
                    }
                    recommendedProductItemLiveData.value = products
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchFlashSale() {

        dbAuthors.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var products = mutableListOf<ProductItem>()
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(ProductItem::class.java)
                        if (product?.isFlashSale == true) {
                            product.let {
                                Log.e("flashSale", it.toString())

                                products.add(it)
                            }
                        }
                    }
                    flashSaleLiveData.value = products
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchMegaSale() {
        dbAuthors.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var products = mutableListOf<ProductItem>()
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(ProductItem::class.java)
                        if (product?.isMegaSale == true) {
                            product.let {
                                products.add(it)
                            }
                        }
                    }
                    megaSaleLiveData.value = products
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchSearchItems(categoryId: String) {
        dbAuthors.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var products = mutableListOf<ProductItem>()
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(ProductItem::class.java)
                        if (product?.categoryId == categoryId) {
                            product.let {
                                products.add(it)
                            }
                        }
                    }
                    searchProductItemLiveData.value = products
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchProductDetails(productId: String) {
        Log.e("ProductRepositoryID", productId)
        dbAuthors.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var products = ProductDetails()
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(ProductDetails::class.java)
                        if (product?.id == productId) {
                            product.let {
                                products = it
                            }
                        }
                    }
                    productDetails.value = products
                    Log.e("ProductRepositoryDetailsValue", products.name!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}