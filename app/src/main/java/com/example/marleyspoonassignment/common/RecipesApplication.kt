package com.example.marleyspoonassignment.common

import android.app.Application
import com.example.marleyspoonassignment.base.recipesListAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RecipesApplication)
            modules(recipesListAppModules)
        }
    }
}