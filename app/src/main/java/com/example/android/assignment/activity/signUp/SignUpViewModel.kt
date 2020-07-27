package com.example.android.assignment.activity.signUp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.assignment.repository.SignInRepository
import com.example.android.assignment.database.LoginEntity

class SignUpViewModel : ViewModel() {

    var email : String = ""
    var password : String = ""

    private var _isPresent = MutableLiveData<Boolean>()
    private var _isInserted = MutableLiveData<Boolean>()

    init {
        _isInserted.value = false
        _isPresent.value = false
    }

    fun checkDatabase(context: Context, loginEntity: LoginEntity) : LiveData<Boolean> {

//        Log.d("logging", loginEntity.password)
//        Log.d("logging", loginEntity.email)

        _isPresent.value = SignInRepository(
            context,
            loginEntity,
            1
        ).execute().get()
        return _isPresent
    }

    fun insertDatabase(context: Context, loginEntity: LoginEntity) : LiveData<Boolean> {
        _isInserted.value = SignInRepository(
            context,
            loginEntity,
            2
        ).execute().get()
        return _isInserted
    }

}