package com.example.projectforvk.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectforvk.R
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val images: List<String>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolderPG>() {

    inner class ViewHolderPG(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_view_pager)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPG {
       return ViewHolderPG(LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolderPG, position: Int) {
        val image = images[position]
        Picasso.with(holder.itemView.context).load(image).into(holder.image)
    }
}