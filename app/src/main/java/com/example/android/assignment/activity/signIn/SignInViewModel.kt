package com.example.android.assignment.activity.signIn

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.assignment.repository.SignInRepository
import com.example.android.assignment.database.LoginEntity

class SignInViewModel() : ViewModel() {

    private var _isPresent = MutableLiveData<Boolean>()

    fun checkDatabase(context: Context, loginEntity: LoginEntity) : LiveData<Boolean>{
        _isPresent.value = SignInRepository(
            context,
            loginEntity,
            1
        ).execute().get()
        return _isPresent
    }

}