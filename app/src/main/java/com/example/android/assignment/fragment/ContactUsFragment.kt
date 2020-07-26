package com.example.android.assignment.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.assignment.R


class ContactUsFragment : Fragment() {

    private lateinit var phone: TextView
    private lateinit var email: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_contact_us, container, false)

        phone = view.findViewById(R.id.contact_phone)
        email = view.findViewById(R.id.contact_email)

        val phoneContent = SpannableString("Phone Number : 999999999")
        phoneContent.setSpan(UnderlineSpan(), 0, phoneContent.length, 0)
        phone.text = phoneContent

        val emailContent = SpannableString("xyz@gmail.com")
        emailContent.setSpan(UnderlineSpan(), 0, emailContent.length, 0)
        email.text = emailContent

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