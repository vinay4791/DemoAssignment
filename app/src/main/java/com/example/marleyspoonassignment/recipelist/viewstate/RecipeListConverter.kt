package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.recipelist.backend.ListResponse
import com.example.marleyspoonassignment.rx.Converter
import com.example.marleyspoonassignment.util.Utils

class RecipeListConverter (private val utils: Utils) :
    Converter<ListResponse, RecipeListViewState> {
    override fun apply(listResponse: ListResponse): RecipeListViewState {

        return RecipeListViewState.Success(emptyList())

    }

}