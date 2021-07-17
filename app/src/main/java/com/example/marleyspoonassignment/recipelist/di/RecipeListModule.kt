package com.example.marleyspoonassignment.recipelist.di

import com.example.marleyspoonassignment.recipelist.RecipeListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val recipeListModule = module {

    viewModel {
        RecipeListViewModel()
    }

}