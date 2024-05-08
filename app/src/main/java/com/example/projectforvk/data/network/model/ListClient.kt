package com.example.projectforvk.data.network.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ListClient {

private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttps().build())
                .build()
        }
        return retrofit!!
    }

    private fun okHttps(): OkHttpClient.Builder {
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }
}