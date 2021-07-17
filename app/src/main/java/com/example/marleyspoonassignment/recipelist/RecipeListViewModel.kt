package com.example.marleyspoonassignment.recipelist

import androidx.lifecycle.MutableLiveData
import com.example.marleyspoonassignment.base.BaseViewModel
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import io.reactivex.disposables.CompositeDisposable

class RecipeListViewModel : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val recipeListData = MutableLiveData<RecipeListViewState>()

    fun recipeListData(): MutableLiveData<RecipeListViewState> = recipeListData


}