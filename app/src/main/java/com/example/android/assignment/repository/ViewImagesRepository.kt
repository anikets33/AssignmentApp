package com.example.android.assignment.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.assignment.model.Image
import com.example.android.assignment.services.ImageApi
import com.example.android.assignment.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class ViewImagesRepository {

    fun loadImages(context: Context) : LiveData<List<Image>>{

        val data = MutableLiveData<List<Image>>()

        val imageService = ServiceBuilder.buildService(ImageApi::class.java)

        val requestCall = imageService.getImage()

        requestCall.enqueue(object :
            retrofit2.Callback<List<com.example.android.assignment.model.Image>> {
            override fun onFailure(
                call: Call<List<Image>>?,
                t: Throwable?
            ) {
                Toast.makeText(context, "Error occurred", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<Image>>?,
                response: Response<List<Image>>?
            ) {
                if (response != null) {

                    if (response.isSuccessful) {

                        data.value = response.body()

                    } else {
                        Toast.makeText(context, "Failed to fetch images", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(context, "Some error occurred", Toast.LENGTH_LONG).show()
                }
            }

        })

        return data
    }

}