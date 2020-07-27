package com.example.android.assignment.fragment.viewImage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.assignment.model.Image
import com.example.android.assignment.repository.ViewImagesRepository

class ViewImageViewModel : ViewModel() {

    fun getImages(context: Context) : LiveData<List<Image>> {
        return ViewImagesRepository().loadImages(context)
    }
}