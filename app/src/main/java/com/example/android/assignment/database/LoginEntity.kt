package com.example.android.assignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginEntity (

    @PrimaryKey val email : String,
    val password : String

    )