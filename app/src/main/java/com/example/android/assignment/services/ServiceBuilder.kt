package com.example.android.assignment.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    //url of the api
    private const val url = "https://jsonplaceholder.typicode.com/"//photos omitted for testing retrofit

    //Create OkHttp client
    private val okHttp : OkHttpClient.Builder = OkHttpClient.Builder()

    //Create retrofit builder
    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttp.build())

    //Create retrofit instance
    private val retrofit : Retrofit = builder.build()

    fun<T> buildService(serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }

}