package com.example.android.assignment.fragment.image

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel(){

    private val _data = MutableLiveData<Intent?>()
    val data: LiveData<Intent?>
        get() = _data

    private val _isChanged = MutableLiveData<Boolean>()
    val isChanged: LiveData<Boolean>
        get() = _isChanged

    init{
        _isChanged.value = false
    }

    fun setData(imageData: Intent?){
        _data.value = imageData
        _isChanged.value = true
    }
}