package com.example.marleyspoonassignment.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marleyspoonassignment.api.ErrorType
import com.example.marleyspoonassignment.base.BaseViewModel
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import io.reactivex.disposables.CompositeDisposable

class RecipeListViewModel constructor(private val repository: RecipeListRepository) :
    BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val recipeListData = MutableLiveData<RecipeListViewState>()

    fun recipeListData(): LiveData<RecipeListViewState> = recipeListData

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