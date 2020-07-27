package com.example.android.assignment.activity.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.assignment.R
import com.example.android.assignment.activity.MainActivity
import com.example.android.assignment.database.LoginEntity
import com.google.android.material.snackbar.Snackbar

class SignIn : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn: Button

    private lateinit var model: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        email = findViewById(R.id.sign_in_email)
        password = findViewById(R.id.sign_in_password)
        btn = findViewById(R.id.sign_in_btn)

        model = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        btn.setOnClickListener {

            // Hide the keyboard.
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            checkLogin(email.text.toString(), password.text.toString(), it)

        }

    }

    private fun checkLogin(email: String, password: String, view: View) {

        val loginEntity = LoginEntity(
            email,
            password
        )


        model.checkDatabase(this, loginEntity).observe(this, Observer { resList ->

            if (resList) {

                val snack = Snackbar.make(view, "Login Successful", Snackbar.LENGTH_LONG)
                snack.show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {

                val snack = Snackbar.make(
                    view,
                    "Login Failed. Please check your credentials",
                    Snackbar.LENGTH_LONG
                )
                snack.show()

            }

        })

    }

}