package com.example.ecommerceapp.fragments.explore

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.SaleAdapter
import com.example.ecommerceapp.constants.CATEGORY_ID
import com.example.ecommerceapp.constants.PRODUCT_ID
import com.example.ecommerceapp.databinding.FragmentSearchResultBinding
import com.example.ecommerceapp.model.ProductItem
import com.example.ecommerceapp.viewModel.SearchViewModel
import com.example.ecommerceapp.viewModel.ViewModelFactory

class SearchResult : Fragment(), SaleAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var searchResultAdapter: SaleAdapter
    private lateinit var viewModel: SearchViewModel
    private var searchList: MutableList<ProductItem> = mutableListOf()
    private var searchResults: MutableList<ProductItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory())[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItemsInSearchResult()
        Log.e("categoryID", arguments?.getString(CATEGORY_ID)!!)
        binding.tvBackToHome.setOnClickListener {
            findNavController().setGraph(R.navigation.home)
            findNavController().navigate(R.id.action_global_home)
        }
        Log.e("navController", findNavController().graph.id.toString())
        initAdapters()
        initRecyclerViews()
    }

    private fun initAdapters() {
        searchResultAdapter = SaleAdapter(requireContext(), searchResults, searchResults.size)
        searchResultAdapter.setOnItemClickListener(this)
    }

    private fun initRecyclerViews() {
        binding.rvSearchResults.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemsInSearchResult() {
        viewModel.getSearchItems(arguments?.getString(CATEGORY_ID)!!)
        viewModel.searchProductsItems.observe(viewLifecycleOwner) {
            searchResults.clear()
            searchResults.addAll(it)
            manageSearchUI()
            Log.e("searchResults", searchResults.toString())
            searchResultAdapter.notifyDataSetChanged()
        }

    }

    private fun manageSearchUI() {
        if (searchResults.size == 0) {
            binding.apply {
                ivNoResults.visibility = View.VISIBLE
                tvNoResultsFound.visibility = View.VISIBLE
                tvSearchMessage.visibility = View.VISIBLE
                rvSearchResults.visibility = View.GONE
            }
        } else {
            binding.apply {
                ivNoResults.visibility = View.GONE
                tvNoResultsFound.visibility = View.GONE
                tvSearchMessage.visibility = View.GONE
                rvSearchResults.visibility = View.VISIBLE
            }
        }
        binding.apply {
            tvSearchCategory.text = arguments?.getString(CATEGORY_ID)
            tvTotalResults.text = searchResults.size.toString() + " Results"
        }
    }

    override fun onItemClick(position: Int) {
        val bundle = bundleOf(PRODUCT_ID to searchResults[position].id)
        findNavController().navigate(R.id.action_searchResult_to_detailsPage,bundle)
    }

}