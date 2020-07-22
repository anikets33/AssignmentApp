package com.example.android.assignment.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.assignment.R
import com.example.android.assignment.activity.SignIn
import com.example.android.assignment.database.LoginEntity


class ContactUsFragment : Fragment() {

    private lateinit var phone: TextView
    private lateinit var email: TextView

    var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_contact_us, container, false)

        phone = view.findViewById(R.id.contact_phone)
        email = view.findViewById(R.id.contact_email)

        sharedPreferences = activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

//        phone.text = sharedPreferences?.getString("email", "Email")
//        email.text = sharedPreferences?.getString("password", "Password")

        phone.text = "Phone Number : 999999999"
        email.text = "xyz@gmail.com"

        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
            startActivity(intent)
        }

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            startActivity(intent)
        }

        return view
    }

}