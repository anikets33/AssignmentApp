package com.example.android.assignment.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDao {

    @Insert
    fun insertLogin(loginEntity: LoginEntity)

    @Delete
    fun deleteLogin(loginEntity: LoginEntity)

    @Query("SELECT * FROM login WHERE email = :email and password = :password")
    fun getLoginDetails(email: String, password: String): LoginEntity

}