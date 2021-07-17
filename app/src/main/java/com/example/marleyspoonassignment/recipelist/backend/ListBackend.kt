package com.example.marleyspoonassignment.recipelist.backend

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListBackend {

    @GET("/spaces/{space_id}/environments/{environment_id}/entries")
    fun fetchRecipeListDetails(@Path("space_id") spaceId: String,
                @Path("environment_id") environmentId: String,
                @Query("access_token") accessToken: String): Single<ListResponse>

}