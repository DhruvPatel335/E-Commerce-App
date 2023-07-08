package com.example.ecommerceapp.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.SaleAdapter
import com.example.ecommerceapp.databinding.FragmentOfferScreenBinding
import com.example.ecommerceapp.model.ProductItem

class OfferScreen : Fragment(), SaleAdapter.OnItemClickListener {

    private lateinit var binding: FragmentOfferScreenBinding
    private lateinit var saleAdapter: SaleAdapter
    private var flashSaleProducts: MutableList<ProductItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfferScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        addItemsInMegaSale()
        initAdapters()
        initRecyclerView()
        initClickListeners()

    }

    private fun initAdapters() {
        saleAdapter = SaleAdapter(requireContext(), flashSaleProducts, flashSaleProducts.size)
        saleAdapter.setOnItemClickListener(this)
    }

    private fun initClickListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRecyclerView() {
        binding.rvSuperFlashSale.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = saleAdapter
        }
    }

//    private fun addItemsInMegaSale() {
//        flashSaleProducts.clear()
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//        flashSaleProducts.add(
//            ProductItem(
//                "productId",
//                "Shoes Puma ultimate",
//                R.drawable.offer_three,
//                25,
//                7000,
//                isFlashSale = true,
//                isMegaSale = false,
//                isRecommended = false,
//                "shoes"
//            )
//        )
//
//    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_offerScreen_to_productDetails)
    }
}