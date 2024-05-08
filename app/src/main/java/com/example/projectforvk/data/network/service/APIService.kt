package com.example.projectforvk.data.network.service

import com.example.projectforvk.data.network.model.Object
import com.example.projectforvk.data.network.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("products")
    suspend fun getRequest(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Object

    @GET("products/search")
    suspend fun getProducts(@Query("q") name: String) : Object
}