package com.example.marleyspoonassignment.recipelist.backend

import io.reactivex.Single

interface  ListFetcher {

    fun fetchRecipeListDetails(spaceId: String, environment: String, accessToken: String) : Single<ListResponse>

}