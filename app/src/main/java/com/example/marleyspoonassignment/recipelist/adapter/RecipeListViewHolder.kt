package com.example.marleyspoonassignment.recipelist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeItem
import kotlinx.android.synthetic.main.recipes_item_row.view.*

class RecipeListViewHolder(private val itemView: View,
                           private val listener: Listener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(recipeItem: RecipeItem) {
        itemView.recipe_item_tv.text = recipeItem.title

        Glide.with(itemView.context)
            .load(recipeItem.imageUrl)
            .into(itemView.recipe_iv)

        itemView.setOnClickListener {
            listener.recipeItemSelected(recipeItem.id)
        }

    }

    interface Listener {
        fun recipeItemSelected(recipeItemId: String)
    }

}