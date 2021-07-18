package com.example.marleyspoonassignment.recipelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.base.BaseFragment
import com.example.marleyspoonassignment.recipelist.RecipeListRepository
import com.example.marleyspoonassignment.recipelist.adapter.TagsAdapter
import com.example.marleyspoonassignment.recipelist.viewmodel.RecipeListViewModel
import com.example.marleyspoonassignment.recipelist.viewmodel.RecipeViewModelFactory
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeItem
import com.example.marleyspoonassignment.util.AppConstants.EMPTY_STRING
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.ext.android.inject

class RecipeDetailsFragment : BaseFragment() {

    private lateinit var rootView: View
    private val repository: RecipeListRepository by inject()
    private val viewModel: RecipeListViewModel by activityViewModels() {
        RecipeViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_details, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        observeRecipeListData()
    }

    private fun observeRecipeListData() {
        viewModel.getSelectedRecipeItem()
            .observe(viewLifecycleOwner, Observer { recipeItem ->
                populateRecipeDetails(recipeItem)
            })
    }

    private fun populateRecipeDetails(recipeItem: RecipeItem) {
        Glide.with(requireActivity())
            .load(recipeItem.imageUrl)
            .into(recipe_details_iv)

        description_tv.text = recipeItem.description
        title_tv.text = recipeItem.title
        if(recipeItem.chefName != EMPTY_STRING) {
            chef_name_tv.text = "Chef : ${recipeItem.chefName}"
        }
        setTags(recipeItem.tags)
    }

    private fun setTags(tags: List<String>) {
        val layoutManager = FlexboxLayoutManager(requireActivity())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        tags_recycler_view.layoutManager = layoutManager
        val adapter = TagsAdapter(tags)
        tags_recycler_view.adapter = adapter
    }


}