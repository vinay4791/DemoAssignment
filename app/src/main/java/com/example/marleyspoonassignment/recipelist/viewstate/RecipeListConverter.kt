package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.recipelist.backend.AssetItem
import com.example.marleyspoonassignment.recipelist.backend.ListResponse
import com.example.marleyspoonassignment.recipelist.backend.ResponseBaseItem
import com.example.marleyspoonassignment.rx.Converter
import com.example.marleyspoonassignment.util.AppConstants.EMPTY_STRING
import com.example.marleyspoonassignment.util.AppConstants.RECIPE
import com.example.marleyspoonassignment.util.Utils

class RecipeListConverter(private val utils: Utils) :
    Converter<ListResponse, RecipeListViewState> {
    override fun apply(listResponse: ListResponse): RecipeListViewState {
        val recipeItemList = mutableListOf<RecipeItem>()
        val listResponseItems = listResponse.items
        for (listResponseItem in listResponseItems) {
            if (listResponseItem.sys.contentType.sys.id == RECIPE) {
                recipeItemList.add(getRecipeItem(listResponseItem, listResponse.includes.Asset))
            }
        }
        return RecipeListViewState.Success(recipeItemList)
    }

    private fun getRecipeItem(
        listResponseItem: ResponseBaseItem,
        assets: List<AssetItem>,
    ): RecipeItem {
        var imageUrl = EMPTY_STRING
        for (asset in assets) {
            if (listResponseItem.fields.photo.sys.id == asset.sys.id) {
                imageUrl = utils.appendHttps(asset.fields.file.url)
            }
        }
        return RecipeItem(
            id = listResponseItem.sys.id,
            description = listResponseItem.fields.description,
            title = listResponseItem.fields.title,
            imageUrl = imageUrl
        )
    }

}