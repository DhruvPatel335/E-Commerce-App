package com.example.ecommerceapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.SaleAdapter
import com.example.ecommerceapp.databinding.FragmentFavoriteProductsBinding
import com.example.ecommerceapp.model.ProductItem

class FavoriteProducts : Fragment(), SaleAdapter.OnItemClickListener {
    private lateinit var binding: FragmentFavoriteProductsBinding
    private lateinit var saleAdapter: SaleAdapter
    private var favoriteProducts: MutableList<ProductItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //addItemsToFavoriteProducts()
        initAdapters()
        initRecyclerView()
        initClickListeners()
    }

    private fun initAdapters() {
        saleAdapter = SaleAdapter(requireContext(), favoriteProducts, favoriteProducts.size)
        saleAdapter.setOnItemClickListener(this)
    }

    private fun initClickListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
/*
    private fun addItemsToFavoriteProducts() {
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
        favoriteProducts.add(
            ProductItem(
                "productId",
                "Shoes Puma ultimate",
                R.drawable.offer_three,
                25,
                7000,
                isFlashSale = true,
                isMegaSale = false,
                isRecommended = false,
                "shoes"
            )
        )
    }

 */

    private fun initRecyclerView() {
        binding.rvFavoriteProduct.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = saleAdapter
        }
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_favoriteProducts_to_productDetails)
    }
}