package com.example.marleyspoonassignment.recipelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marleyspoonassignment.R
import com.example.marleyspoonassignment.recipelist.viewstate.RecipeItem

class RecipeListAdapter : RecyclerView.Adapter<RecipeListViewHolder>() {

    private var recipesList: List<RecipeItem> = emptyList()
    private var listener: Listener = Listener.NO_OP

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipes_item_row, parent, false)
        return RecipeListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(recipesList[position])
    }

    override fun getItemCount(): Int = recipesList.size

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setItems(recipesList: List<RecipeItem>) {
        this.recipesList = recipesList
        notifyDataSetChanged()
    }

    interface Listener : RecipeListViewHolder.Listener{
        companion object {
            val NO_OP: Listener = object : Listener {
                override fun recipeItemSelected(recipeItem: RecipeItem) {
                    //NO_OP
                }
            }
        }
    }


}