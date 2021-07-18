package com.example.marleyspoonassignment.recipelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.base.BaseFragment
import com.example.marleyspoonassignment.recipelist.RecipeListRepository
import com.example.marleyspoonassignment.recipelist.RecipeListViewModel
import com.example.marleyspoonassignment.recipelist.RecipeViewModelFactory
import com.example.marleyspoonassignment.recipelist.adapter.RecipeListAdapter
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeItem
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import com.example.marleyspoonassignment.util.AppConstants
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject

class RecipeListFragment : BaseFragment() {

    private lateinit var rootView: View
    private val repository: RecipeListRepository by inject()
    private val viewModel: RecipeListViewModel by activityViewModels() {
        RecipeViewModelFactory(repository)
    }
    private val adapter: RecipeListAdapter by inject()
    private var isApiLoading = false

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
        setUpCartRecyclerView()
        getRecipeList()
        showApiLoadingIndicator()

        list_swipe_layout.setOnRefreshListener {
            if (!isApiLoading) {
                getRecipeList()
                showApiLoadingIndicator()
            } else {
                list_swipe_layout.isRefreshing = false
            }
        }

    }

    private fun getRecipeList() {
        viewModel.fetchRecipeList(
            AppConstants.SPACE_ID,
            AppConstants.ENVIRONMENT,
            AppConstants.ACCESS_TOKEN
        )
    }

    private fun showApiLoadingIndicator() {
        isApiLoading = true
        loadingView.showLoading(R.color.loader_bg_white_transparent)
    }

    private fun hideApiLoadingIndicator() {
        isApiLoading = false
        if (list_swipe_layout.isRefreshing) {
            list_swipe_layout.isRefreshing = false
        }
        loadingView.hideLoading()
    }

    private fun observeRecipeListData() {
        viewModel.recipeListData()
            .observe(viewLifecycleOwner, Observer { recipeListData ->
                when (recipeListData) {
                    is RecipeListViewState.Loading -> showApiLoadingIndicator()
                    is RecipeListViewState.Success -> {
                        populateRecipes(recipeListData.recipeInfoList)
                        hideApiLoadingIndicator()
                    }
                    is RecipeListViewState.Error -> {
                        hideApiLoadingIndicator()
                    }
                }
            })
    }

    private fun setUpCartRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        adapter.setListener(listener)
    }

    private fun populateRecipes(recipesInfoList: List<RecipeItem>) {
        adapter.setItems(recipesInfoList)
    }

    private val listener = object : RecipeListAdapter.Listener {
        override fun recipeItemSelected(recipeItem: RecipeItem) {
            viewModel.setSelectedRecipeItem(recipeItem)
            Navigation.findNavController(rootView)
                .navigate(R.id.action_listFragment_to_detailFragment)
        }

    }

}