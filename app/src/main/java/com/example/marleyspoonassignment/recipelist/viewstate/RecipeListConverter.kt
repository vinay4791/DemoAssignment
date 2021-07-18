package com.example.marleyspoonassignment.recipelist.viewstate

import com.example.marleyspoonassignment.recipelist.backend.AssetItem
import com.example.marleyspoonassignment.recipelist.backend.ListResponse
import com.example.marleyspoonassignment.recipelist.backend.ResponseBaseItem
import com.example.marleyspoonassignment.recipelist.backend.TagItem
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
                var chefName = EMPTY_STRING
                var tags = emptyList<String>()
                if (listResponseItem.fields.chef != null) {
                    chefName = getChefName(listResponseItem.fields.chef.sys.id, listResponseItems)
                }
                if(!listResponseItem.fields.tags.isNullOrEmpty()){
                    tags = getTags(listResponseItem.fields.tags, listResponseItems)
                }
                recipeItemList.add(getRecipeItem(listResponseItem, listResponse.includes.Asset, chefName, tags))
            }
        }
        return RecipeListViewState.Success(recipeItemList)
    }

    private fun getRecipeItem(
        listResponseItem: ResponseBaseItem,
        assets: List<AssetItem>,
        chefName: String,
        tags: List<String>,
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
            imageUrl = imageUrl,
            chefName = chefName,
            tags = tags
        )
    }

    private fun getChefName(id: String, listResponseItems: List<ResponseBaseItem>): String {
        for (listResponseItem in listResponseItems) {
            if (listResponseItem.sys.id == id) {
                return listResponseItem.fields.name
            }
        }
        return EMPTY_STRING
    }

    private fun getTags(tags: List<TagItem>, listResponseItems: List<ResponseBaseItem>): List<String>{
        val recipeItemList = mutableListOf<String>()
        tags.map {
            for(listResponseItem in listResponseItems) {
                if(it.sys.id == listResponseItem.sys.id) {
                    recipeItemList.add(listResponseItem.fields.name)
                }
            }
        }
        return recipeItemList
    }

}