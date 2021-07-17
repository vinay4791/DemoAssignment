package com.example.marleyspoonassignment.recipelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.base.BaseFragment
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import com.example.marleyspoonassignment.util.AppConstants
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : BaseFragment() {

    private lateinit var rootView: View
    private val viewModel: RecipeListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        observeRecipeListData()
        getRecipeList()
        showApiLoadingIndicator()
    }

    private fun getRecipeList() {
        viewModel.fetchRecipeList(
            AppConstants.SPACE_ID,
            AppConstants.ENVIRONMENT,
            AppConstants.ACCESS_TOKEN
        )
    }

    private fun showApiLoadingIndicator() {
        loadingView.showLoading(R.color.loader_bg_white_transparent)
    }

    private fun hideApiLoadingIndicator() {
        loadingView.hideLoading()
    }

    private fun observeRecipeListData() {
        viewModel.recipeListData()
            .observe(viewLifecycleOwner, Observer { recipeListData ->
                when (recipeListData) {
                    is RecipeListViewState.Loading -> showApiLoadingIndicator()
                    is RecipeListViewState.Success -> {
                       // showPopularMoviesListData(moviesListData.moviesInfoList)
                        hideApiLoadingIndicator()
                    }
                    is RecipeListViewState.Error -> {
                        hideApiLoadingIndicator()
                    }
                }
            })
    }

}