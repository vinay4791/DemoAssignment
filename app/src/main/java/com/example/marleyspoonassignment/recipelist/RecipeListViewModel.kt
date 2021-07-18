package com.example.marleyspoonassignment.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marleyspoonassignment.api.ErrorType
import com.example.marleyspoonassignment.base.BaseViewModel
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeItem
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import io.reactivex.disposables.CompositeDisposable

class RecipeListViewModel constructor(private val repository: RecipeListRepository) :
    BaseViewModel() {

     val hello = "hello"

    private val disposable = CompositeDisposable()
    private val recipeListData = MutableLiveData<RecipeListViewState>()
    private val selectedRecipeItem = MutableLiveData<RecipeItem>()

    fun recipeListData(): LiveData<RecipeListViewState> = recipeListData
    fun getSelectedRecipeItem(): LiveData<RecipeItem> = selectedRecipeItem

    fun setSelectedRecipeItem(recipeItem: RecipeItem) {
        selectedRecipeItem.setValue(recipeItem)
    }

    fun fetchRecipeList(
        spaceId: String,
        environment: String,
        accessToken: String
    ) {
        disposable.add(
            repository.fetchRecipeListDetails(spaceId, environment, accessToken)
                .subscribe({ response ->
                    recipeListData.postValue(response)
                },
                    { error ->
                        recipeListData.postValue(RecipeListViewState.Error(ErrorType.UNKNOWN))
                    })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}