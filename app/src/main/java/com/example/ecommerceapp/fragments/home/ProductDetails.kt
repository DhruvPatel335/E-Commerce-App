package com.example.ecommerceapp.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.constants.PRODUCT_ID
import com.example.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.example.ecommerceapp.model.ProductDetails
import com.example.ecommerceapp.viewModel.HomeViewModel
import com.example.ecommerceapp.viewModel.ProductDetailViewModel
import com.example.ecommerceapp.viewModel.ViewModelFactory

class ProductDetails : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductDetailViewModel
    private var productDetails: ProductDetails = ProductDetails()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory())[ProductDetailViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        viewModel.fetchProductDetails(arguments?.getString(PRODUCT_ID)!!)
        viewModel.productDetails.observe(viewLifecycleOwner) {
            Log.e("productId", it.toString())
            productDetails = it
            setProductDetails()
        }
        Log.e("navController", findNavController().graph.id.toString())
    }

    private fun setProductDetails() {
        binding.apply {
            tvProductName.text = productDetails.name
            tvProductNameDescription.text = productDetails.name
            rbStarRating.rating = productDetails.starRating
            val discount =
                (productDetails.originalPrice!!.toDouble() - productDetails.originalPrice!!.toDouble() * (productDetails.discount!!.toDouble() / 100))
            tvPrice.text = discount.toString()
            tvShownSpecificationDesc.text = productDetails.shownSpecification
            tvStyleSpecificationDesc.text = productDetails.styleSpecification
            tvSpecsDescription.text = productDetails.specificationDescription
            Glide.with(requireContext()).load(productDetails.image).into(binding.vpCarousel)
        }

    }

    private fun initClickListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}