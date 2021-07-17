package com.example.marleyspoonassignment.base

import com.example.marleyspoonassignment.api.networkModule
import com.example.marleyspoonassignment.recipelist.di.recipeListModule
import com.example.marleyspoonassignment.util.utilsModule

val recipesListAppModules = listOf(
    networkModule,
    utilsModule,
    recipeListModule
)