package com.example.ecommerceapp.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.CategoryAdapter
import com.example.ecommerceapp.adapter.SaleAdapter
import com.example.ecommerceapp.adapter.SlideAdapter
import com.example.ecommerceapp.constants.*
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.ProductItem
import com.example.ecommerceapp.model.SlideItem
import com.example.ecommerceapp.viewModel.HomeViewModel
import com.example.ecommerceapp.viewModel.ViewModelFactory
import kotlin.math.abs

class Home : Fragment(), CategoryAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewPager2: ViewPager2
    private var carouselItemList: MutableList<SlideItem> = mutableListOf()
    private var categoryItemList: MutableList<Category> = mutableListOf()
    private lateinit var flashSaleAdapter: SaleAdapter
    private lateinit var megaSaleAdapter: SaleAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recommendedProductsAdapter: SaleAdapter
    private var flashSaleList: MutableList<ProductItem> = mutableListOf()
    private var megaSaleList: MutableList<ProductItem> = mutableListOf()
    private var recommendedProductsList: MutableList<ProductItem> = mutableListOf()
    private var carousalHandler: Handler = Handler()
    private var carousalRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }
    private var resetCarousel = Runnable {
        viewPager2.currentItem = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("navController", findNavController().graph.id.toString())
        addItemsToHomePageCarousel()
        initViewPager()
        setViewPagerAutoScroll()
        addItemsInCategory()
        addItemsInMegaSale()
        addItemsInFlashSale()
        addItemsInRecommendedProducts()
        initAdapters()
        initRecyclerView()
        initClickListeners()
    }

    private fun initAdapters() {
        flashSaleAdapter = SaleAdapter(
            requireContext(),
            flashSaleList,
            itemCount = checkForItemSize(flashSaleList)
        )
        flashSaleAdapter.setOnItemClickListener(object : SaleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = bundleOf(PRODUCT_ID to flashSaleList[position].id)
                findNavController().navigate(R.id.action_home_to_productDetails, bundle)
            }

        })

        megaSaleAdapter = SaleAdapter(
            requireContext(),
            megaSaleList,
            itemCount = checkForItemSize(megaSaleList)
        )
        megaSaleAdapter.setOnItemClickListener(object : SaleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = bundleOf(PRODUCT_ID to megaSaleList[position].id)
                findNavController().navigate(R.id.action_home_to_productDetails, bundle)
            }

        })
        recommendedProductsAdapter = SaleAdapter(
            requireContext(),
            recommendedProductsList,
            itemCount = checkForItemSize(recommendedProductsList)
        )
        recommendedProductsAdapter.setOnItemClickListener(object : SaleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = bundleOf(PRODUCT_ID to recommendedProductsList[position].id)
                findNavController().navigate(R.id.action_home_to_productDetails, bundle)
            }

        })
        categoryAdapter = CategoryAdapter(
            requireContext(),
            categoryItemList,
            itemCount = checkForItemSize(categoryItemList)
        )
        categoryAdapter.setOnItemClickListener(this)
    }

    private fun initClickListeners() {
        binding.tvMoreFlashSale.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_offerScreen)
        }
        binding.ivLove.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_favoriteProducts)
        }
        binding.tvMoreCategores.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_explore)
        }
    }

    private fun addItemsInMegaSale() {
        viewModel.fetchMegaSale()
        viewModel.megaSaleItems.observe(viewLifecycleOwner) {
            Log.e("ProductItems", it.toString())
            megaSaleList.clear()
            megaSaleList.addAll(it)
            megaSaleAdapter.notifyDataSetChanged()
        }

    }

    private fun addItemsInCategory() {
        categoryItemList.clear()
        categoryItemList.add(Category(MAN_SHIRT, "Man Shirt", R.drawable.man_shirt))
        categoryItemList.add(Category(WOMAN_HIGH_HEELS, "High Heels", R.drawable.high_heels))
        categoryItemList.add(Category(WOMAN_BAG, "Woman Bag", R.drawable.woman_bag))
        categoryItemList.add(Category(WOMAN_BIKINI, "Bikini", R.drawable.women_bikini))
        categoryItemList.add(Category(MAN_PANTS, "Man Pants", R.drawable.man_pants))
        categoryItemList.add(Category(WOMAN_PANTS, "Woman Pants", R.drawable.woman_pants))
        categoryItemList.add(Category(MAN_SHOES, "Man Shoes", R.drawable.man_shoes))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemsInRecommendedProducts() {
        viewModel.fetchRecommendedProducts()
        viewModel.recommendedProductItems.observe(viewLifecycleOwner) {
            Log.e("ProductItems", it.toString())
            recommendedProductsList.clear()
            recommendedProductsList.addAll(it)
            recommendedProductsAdapter.notifyDataSetChanged()
        }
    }

    private fun addItemsInFlashSale() {
        viewModel.fetchFlashSale()
        viewModel.flashSaleItems.observe(viewLifecycleOwner) {
            Log.e("ProductItems", it.toString())
            flashSaleList.clear()
            flashSaleList.addAll(it)
            flashSaleAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {

        binding.rvCategories.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
        binding.rvFlashSale.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = flashSaleAdapter
        }

        binding.rvMegaSale.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = megaSaleAdapter
        }

        binding.rvRecommendedProducts.apply {
            layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = recommendedProductsAdapter
        }
    }

    private fun checkForItemSize(productItemList: List<*>): Int {
        var itemCount = productItemList.size
        if (productItemList.size > 10) {
            itemCount = 10
        }
        return itemCount
    }

    private fun setViewPagerAutoScroll() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            var r: Float = abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                carousalHandler.removeCallbacks(carousalRunnable)
                carousalHandler.postDelayed(carousalRunnable, 3000)
                Log.e("pagePosition", position.toString())
                if (position == 4) {
                    viewPager2.postDelayed(resetCarousel, 3000)
                }
            }

        }
        )
    }

    private fun initViewPager() {
        viewPager2 = binding.vpCarousel
        viewPager2.adapter = SlideAdapter(requireContext(), carouselItemList, viewPager2)
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        viewPager2.offscreenPageLimit = 5
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun addItemsToHomePageCarousel() {
        carouselItemList.clear()
        carouselItemList.add(SlideItem(R.drawable.promotion_image_one))
        carouselItemList.add(SlideItem(R.drawable.promotion_image_two))
        carouselItemList.add(SlideItem(R.drawable.promotion_image_one))
        carouselItemList.add(SlideItem(R.drawable.promotion_image_two))
        carouselItemList.add(SlideItem(R.drawable.promotion_image_one))
    }

    override fun onCategoryItemClick(position: Int) {
        val bundle = bundleOf(CATEGORY_ID to categoryItemList[position].id)
        findNavController().setGraph(R.navigation.explore)
        findNavController().navigate(R.id.action_global_searchResult, bundle)
    }
}