package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.api.ErrorType

sealed class RecipeListViewState {

    object Loading : RecipeListViewState()

    data class Success(val recipeInfoList : List<RecipeItem>) : RecipeListViewState()

    data class Error(val errorType : ErrorType) : RecipeListViewState()

}
