package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.util.AppConstants.EMPTY_STRING

data class RecipeItem(
    var id: String,
    var title: String,
    var description: String,
    var imageUrl: String,
    var chefName: String,
    var tags: List<String>
)  {
    companion object {
        val EMPTY = RecipeItem (
            id = EMPTY_STRING,
            title = EMPTY_STRING,
            description = EMPTY_STRING,
            imageUrl = EMPTY_STRING,
            chefName = EMPTY_STRING,
            tags = emptyList()
        )
    }
}