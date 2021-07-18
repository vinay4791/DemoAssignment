package com.example.marleyspoonassignment.recipelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marleyspoonassignment.recipelist.RecipeListRepository

class RecipeViewModelFactory(
    private val repository: RecipeListRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RecipeListViewModel(repository) as T
}