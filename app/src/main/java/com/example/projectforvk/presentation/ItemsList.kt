package com.example.projectforvk.presentation

import com.example.projectforvk.data.network.model.Products

sealed class ItemsList {

    data class ProductsItemList(val products: Products) : ItemsList()

}
