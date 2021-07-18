package com.example.marleyspoonassignment.recipelist.di

import com.example.marleyspoonassignment.recipelist.RecipeListRepository
import com.example.marleyspoonassignment.recipelist.RecipeListViewModel
import com.example.marleyspoonassignment.recipelist.adapter.RecipeListAdapter
import com.example.marleyspoonassignment.recipelist.backend.ListApiFetcher
import com.example.marleyspoonassignment.recipelist.backend.ListBackend
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeListConverter
import com.example.marleyspoonassignment.rx.AndroidSchedulingStrategyFactory
import com.example.marleyspoonassignment.util.AppConstants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val recipeListModule = module {

    factory {
        RecipeListAdapter()
    }

    factory {
        listBackend(get())
    }

    factory {
        ListApiFetcher(get())
    }

    factory {
        RecipeListConverter(get())
    }

    factory {
        RecipeListRepository(
            get(),
            get(), AndroidSchedulingStrategyFactory.io()
        )
    }

    /*viewModel {
        RecipeListViewModel(get())
    }
*/
}

fun listBackend(retrofit: Retrofit.Builder): ListBackend {
    return retrofit.baseUrl(AppConstants.BASE_URL).build().create(ListBackend::class.java)
}