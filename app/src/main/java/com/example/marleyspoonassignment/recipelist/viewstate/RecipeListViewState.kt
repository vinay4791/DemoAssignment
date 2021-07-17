package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.api.ErrorType

sealed class RecipeListViewState {

    object Loading : RecipeListViewState()

    data class Success(val moviesInfoList : List<RecipeItem>) : RecipeListViewState()

    data class Error(val errorType : ErrorType) : RecipeListViewState()

}
