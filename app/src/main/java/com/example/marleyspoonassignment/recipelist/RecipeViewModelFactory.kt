package com.example.marleyspoonassignment.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecipeViewModelFactory(
    private val repository: RecipeListRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RecipeListViewModel(repository) as T
}