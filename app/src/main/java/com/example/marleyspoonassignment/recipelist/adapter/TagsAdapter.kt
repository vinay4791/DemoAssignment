package com.example.marleyspoonassignment.recipelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marleyspoonassignment.R

class TagsAdapter(private val mItems: List<String>) :
    RecyclerView.Adapter<TagsAdapter.DivItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagsAdapter.DivItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tags_item_layout, parent, false)
        return DivItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DivItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class DivItemViewHolder constructor(itemView: View) : RecyclerView.ViewHolder
        (itemView) {
        private val text: TextView = itemView.findViewById(R.id.tags_tv)

        fun onBind(position: Int) {
            text.text = mItems[position]
        }
    }
}