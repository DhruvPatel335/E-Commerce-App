package com.example.ecommerceapp.fragments.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.CategoryAdapter
import com.example.ecommerceapp.constants.*
import com.example.ecommerceapp.databinding.FragmentExploreBinding
import com.example.ecommerceapp.model.Category

class Explore : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private lateinit var manCategoryAdapter: CategoryAdapter
    private lateinit var womanCategoryAdapter: CategoryAdapter
    private var manCategoryItemList: MutableList<Category> = mutableListOf()
    private var womanCategoryItemList: MutableList<Category> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItemsInCategory()
        initAdapters()
        initRecyclerViews()
    }

    private fun initRecyclerViews() {
        binding.rvManFashion.layoutManager =
            GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        binding.rvManFashion.adapter = manCategoryAdapter
        binding.rvWomanFashion.layoutManager =
            GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        binding.rvWomanFashion.adapter = womanCategoryAdapter
    }

    private fun initAdapters() {
        manCategoryAdapter =
            CategoryAdapter(requireContext(), manCategoryItemList, manCategoryItemList.size)
        manCategoryAdapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onCategoryItemClick(position: Int) {
                val bundle = bundleOf(CATEGORY_ID to manCategoryItemList[position].id)
                findNavController().navigate(R.id.action_explore_to_searchResult, bundle)

            }
        })
        womanCategoryAdapter =
            CategoryAdapter(requireContext(), womanCategoryItemList, womanCategoryItemList.size)
        womanCategoryAdapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onCategoryItemClick(position: Int) {
                val bundle = bundleOf(CATEGORY_ID to womanCategoryItemList[position].id)
                findNavController().navigate(R.id.action_explore_to_searchResult, bundle)

            }
        })
    }

    private fun addItemsInCategory() {
        manCategoryItemList.clear()
        manCategoryItemList.add(Category(MAN_SHIRT, "Man Shirt", R.drawable.man_shirt))
        manCategoryItemList.add(
            Category(
                MAN_EQUIPMENT_WORK,
                "Man Work Equipment",
                R.drawable.man_work_equipment
            )
        )
        manCategoryItemList.add(Category(MAN_T_SHIRT, "Man T-Shirt", R.drawable.man_t_shirts))
        manCategoryItemList.add(Category(MAN_SHOES, "Man Shoes", R.drawable.man_shoes))
        manCategoryItemList.add(Category(MAN_PANTS, "Man Pants", R.drawable.man_pants))
        manCategoryItemList.add(
            Category(
                MAN_UNDERWEAR,
                "Man Underwear",
                R.drawable.man_underwear
            )
        )


        womanCategoryItemList.clear()
        womanCategoryItemList.add(Category(WOMAN_DRESS, "Dress", R.drawable.dress))
        womanCategoryItemList.add(
            Category(
                WOMAN_T_SHIRT,
                "Woman T-Shirt",
                R.drawable.woman_t_shirt
            )
        )
        womanCategoryItemList.add(Category(WOMAN_PANTS, "Woman Pants", R.drawable.woman_pants))
        womanCategoryItemList.add(Category(WOMAN_SKIRT, "Skirt", R.drawable.skirt))
        womanCategoryItemList.add(Category(WOMAN_BAG, "Woman Bag", R.drawable.woman_bag))
        womanCategoryItemList.add(
            Category(
                WOMAN_HIGH_HEELS,
                "High Heels",
                R.drawable.high_heels
            )
        )
        womanCategoryItemList.add(
            Category(
                WOMAN_BIKINI,
                "Bikini",
                R.drawable.women_bikini
            )
        )
    }
}