package com.example.android.assignment.repository

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.example.android.assignment.database.AssignmentDatabase
import com.example.android.assignment.database.LoginEntity

class SignInRepository(val context: Context, private val loginEntity: LoginEntity, private val mode: Int) :
    AsyncTask<Void, Void, Boolean>() {

    /*
    mode 1 -> check db if login details is present in database or not
    mode 2 -> add login details to database
    mode 3 -> delete login details from database
    */

    private val db: AssignmentDatabase =
        Room.databaseBuilder(context, AssignmentDatabase::class.java, "res-db").build()

    override fun doInBackground(vararg params: Void?): Boolean {
        when (mode) {

            1 -> {
                val login: LoginEntity? = db.loginDao().getLoginDetails(
                    loginEntity.email, loginEntity.password
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