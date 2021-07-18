package com.example.marleyspoonassignment.recipelist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.base.BaseFragment
import com.example.marleyspoonassignment.recipelist.RecipeListRepository
import com.example.marleyspoonassignment.recipelist.RecipeListViewModel
import com.example.marleyspoonassignment.recipelist.RecipeViewModelFactory
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
                    Log.d("vinay", recipeItem.description)
            })
    }


}