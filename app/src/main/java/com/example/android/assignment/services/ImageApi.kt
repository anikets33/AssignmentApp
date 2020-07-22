package com.example.android.assignment.services

import com.example.android.assignment.model.Image
import retrofit2.Call
import retrofit2.http.GET

interface ImageApi {

    @GET("photos")
    fun getImage() : Call<List<Image>>
}