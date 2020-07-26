package com.example.android.assignment.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.android.assignment.R
import com.example.android.assignment.database.AssignmentDatabase
import com.example.android.assignment.database.LoginEntity
import com.google.android.material.snackbar.Snackbar

class SignIn : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        email = findViewById(R.id.sign_in_email)
        password = findViewById(R.id.sign_in_password)
        btn = findViewById(R.id.sign_in_btn)

        btn.setOnClickListener {

            // Hide the keyboard.
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            checkLogin(email.text.toString(), password.text.toString(), it)

        }

    }

    private fun checkLogin(email: String, password: String, view: View){

        val loginEntity = LoginEntity(
            email,
            password
        )

        val checkDatabase = DBAsyncTask(this, loginEntity, 1).execute().get()
        if (checkDatabase) {

            val snack = Snackbar.make(view,"Login Successful", Snackbar.LENGTH_LONG)
            snack.show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {

            val snack = Snackbar.make(view,"Login Failed. Please check your credentials", Snackbar.LENGTH_LONG)
            snack.show()

        }

    }

    class DBAsyncTask(val context: Context, val loginEntity: LoginEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        /*
        mode 1 -> check db if login details is present in database or not
        mode 2 -> add login details to database
        mode 3 -> delete login details from database
        */

        val db: AssignmentDatabase =
            Room.databaseBuilder(context, AssignmentDatabase::class.java, "res-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when (mode) {

                1 -> {
                    val login: LoginEntity? = db.loginDao().getLoginDetails(
                        loginEntity.email
                    )
                    db.close()
                    return login != null
                }

                2 -> {
                    db.loginDao().insertLogin(loginEntity)
                    db.close()
                    return true
                }

                3 -> {
                    db.loginDao().deleteLogin(loginEntity)
                    db.close()
                    return true
                }

            }
            return false
        }

    }

    class RetrieveLogin(val context: Context, val email: String) : AsyncTask<Void, Void, LoginEntity>(){
        override fun doInBackground(vararg params: Void?): LoginEntity {
            val db = Room.databaseBuilder(context, AssignmentDatabase::class.java, "res-db").build()

            return db.loginDao().getLoginDetails(email)
        }

    }

}