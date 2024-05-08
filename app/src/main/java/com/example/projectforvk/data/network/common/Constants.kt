package com.example.projectforvk.data.network.common

import com.example.projectforvk.data.network.model.ListClient
import com.example.projectforvk.data.network.service.APIService

object Constants {

    private const val BASE_URL = "https://dummyjson.com/"

    val apiService: APIService
        get() = ListClient.getClient(BASE_URL).create(APIService::class.java)

}

