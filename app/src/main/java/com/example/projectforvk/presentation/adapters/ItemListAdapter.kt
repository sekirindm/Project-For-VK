package com.example.projectforvk.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectforvk.R
import com.example.projectforvk.data.network.model.Products
import com.example.projectforvk.presentation.ItemsList
import com.example.projectforvk.presentation.ListDiffUtils
import com.squareup.picasso.Picasso

class ItemListAdapter(val handleWords: (productId: Products) -> Unit)
    : ListAdapter<ItemsList, RecyclerView.ViewHolder>(ListDiffUtils)
{

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position) is ItemsList.ProductsItemList -> 0
            else -> {
                1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
           is ItemListViewHolder -> {
               val item = getItem(position) as ItemsList.ProductsItemList
               val products = item.products
               holder.bind(products)
           }
        }
    }

    inner class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val price: TextView = itemView.findViewById(R.id.tv_price)
//        private val description: TextView = itemView.findViewById(R.id.tv_description)
        private val rating: TextView = itemView.findViewById(R.id.tv_rating)
        private val image: ImageView = itemView.findViewById(R.id.iv_image)

        fun bind(products: Products) {
            title.text = products.title
            price.text = products.price.toString() + "$"
            rating.text = products.rating.toString()
//            description.text = products.description
            Picasso.with(itemView.context).load(products.thumbnail).into(image)

            itemView.setOnClickListener {
                handleWords(products)

            }
        }
    }
}