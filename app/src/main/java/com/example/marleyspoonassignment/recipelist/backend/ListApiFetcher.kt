package com.example.marleyspoonassignment.recipelist.backend

import io.reactivex.Single

class ListApiFetcher constructor(private val listBackend: ListBackend) : ListFetcher {

    override fun fetchRecipeListDetails(
        spaceId: String,
        environment: String,
        accessToken: String
    ): Single<ListResponse> {
        return listBackend.fetchRecipeListDetails(spaceId, environment, accessToken)
    }

}