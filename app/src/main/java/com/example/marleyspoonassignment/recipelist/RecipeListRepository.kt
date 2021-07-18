package com.example.marleyspoonassignment.recipelist

import com.example.marleyspoonassignment.api.ErrorType
import com.example.marleyspoonassignment.recipelist.backend.ListApiFetcher
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListConverter
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListViewState
import com.example.marleyspoonassignment.rx.SchedulingStrategyFactory
import com.squareup.moshi.JsonEncodingException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.net.UnknownHostException

class RecipeListRepository constructor(
    private val listApiFetcher: ListApiFetcher,
    private val recipesListConverter: RecipeListConverter,
    private val schedulingStrategyFactory: SchedulingStrategyFactory
) {

    fun fetchRecipeListDetails(spaceId: String, environment: String, accessToken: String): Observable<RecipeListViewState> {

        val recipeListApiFetcherObservable = listApiFetcher.
        fetchRecipeListDetails(spaceId, environment, accessToken).toObservable()

        return recipeListApiFetcherObservable
            .map(recipesListConverter)
            .startWith(RecipeListViewState.Loading)
            .compose(ErrorConverter())
            .compose(schedulingStrategyFactory.create())

    }

    class ErrorConverter : ObservableTransformer<RecipeListViewState, RecipeListViewState> {
        override fun apply(upstream: Observable<RecipeListViewState>): ObservableSource<RecipeListViewState> {
            return upstream.onErrorResumeNext(Function<Throwable, ObservableSource<RecipeListViewState>> { cause ->
                Observable.just(convertToCause(cause))
            })
        }

        private fun convertToCause(cause: Throwable): RecipeListViewState {
            return when (cause) {
                is JsonEncodingException -> RecipeListViewState.Error(ErrorType.UNKNOWN)
                is UnknownHostException -> RecipeListViewState.Error(ErrorType.NO_INTERNET_CONNECTION)
                is HttpException -> {
                    RecipeListViewState.Error(ErrorType.SERVER)
                }
                else -> RecipeListViewState.Error(ErrorType.UNKNOWN)
            }
        }

    }

}