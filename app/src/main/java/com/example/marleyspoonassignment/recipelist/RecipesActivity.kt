package com.example.marleyspoonassignment.recipelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.marleyspoonassignment.R

class RecipesActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        navController = Navigation.findNavController(this, R.id.fragment)
    }
}