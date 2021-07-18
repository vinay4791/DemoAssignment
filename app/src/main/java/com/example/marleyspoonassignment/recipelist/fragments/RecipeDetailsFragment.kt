package com.example.marleyspoonassignment.recipelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.base.BaseFragment

class RecipeDetailsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

}