package com.example.android.assignment.activity.signUp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.android.assignment.R
import com.example.android.assignment.activity.MainActivity
import com.example.android.assignment.activity.signIn.SignIn
import com.example.android.assignment.database.LoginEntity
import com.google.android.material.snackbar.Snackbar

class SignUp : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var password: EditText
    private lateinit var btn: Button

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val namePattern = "^[a-zA-Z]*"

    private lateinit var model: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.sign_up_email)
        password = findViewById(R.id.sign_up_password)
        confirmPassword = findViewById(R.id.sign_up_confirm_password)
        name = findViewById(R.id.sign_up_name)
        btn = findViewById(R.id.sign_up_btn)

        model = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        btn.setOnClickListener {

            // Hide the keyboard.
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            if (checkForEmptyField()){
                if (validate(it)){
                    insertLogin(email.text.toString(), password.text.toString())
                }
            }
        }

    }

    private fun insertLogin(email: String, password: String){

        val loginEntity = LoginEntity(
            email,
            password
        )

        val res = model.checkDatabase(this, loginEntity).value
            if(!res!!) {
                val result = model.insertDatabase(this, loginEntity).value
                if(result!!){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Some error occurred!!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Account already exist", Toast.LENGTH_LONG).show()
                val intent = Intent(this, SignIn::class.java)
                startActivity(intent)
            }

    }

    private fun validate(view: View): Boolean{
        when{
            !email.text.toString().trim().matches(emailPattern.toRegex()) ->{
                val snack = Snackbar.make(view,"Invalid Email", Snackbar.LENGTH_LONG)
                snack.show()
                return false
            }
            !name.text.toString().trim().matches(namePattern.toRegex()) ->{
                val snack = Snackbar.make(view,"Name can contain letters only", Snackbar.LENGTH_LONG)
                snack.show()
                return false
            }
            password.text.toString() != confirmPassword.text.toString() ->{
                val snack = Snackbar.make(view,"Password does not match", Snackbar.LENGTH_LONG)
                snack.show()
                return false
            }
        }
        return true
    }

    private fun checkForEmptyField(): Boolean{

        when{
            email.text.toString().isEmpty() -> {
                email.error = "Required Field"
                return false
            }
            name.text.toString().isEmpty() -> {
                name.error = "Required Field"
                return false
            }
            password.text.toString().isEmpty() -> {
                password.error = "Required Field"
                return false
            }
            confirmPassword.text.toString().isEmpty() -> {
                confirmPassword.error = "Required Field"
                return false
            }
        }

        return true
    }

}