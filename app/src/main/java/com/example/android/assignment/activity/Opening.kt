package com.example.android.assignment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android.assignment.R
import com.example.android.assignment.activity.signIn.SignIn
import com.example.android.assignment.activity.signUp.SignUp

class Opening : AppCompatActivity() {

    private lateinit var signInBtn: Button
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        signInBtn = findViewById(R.id.sign_in)
        signUpBtn = findViewById(R.id.sign_up)

        signInBtn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}