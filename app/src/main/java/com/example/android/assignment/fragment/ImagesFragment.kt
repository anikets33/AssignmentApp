package com.example.android.assignment.fragment

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.android.assignment.R
import kotlinx.android.synthetic.*


class ImagesFragment : Fragment() {

    private val RESULT_LOAD_IMAGE = 1
    private lateinit var galleryBtn: Button
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_images, container, false)

        galleryBtn = view.findViewById(R.id.image_btn)
        image = view.findViewById(R.id.image_img)

        image.visibility = View.GONE
        galleryBtn.visibility = View.VISIBLE

        galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_LOAD_IMAGE)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){

            galleryBtn.visibility = View.GONE

            image.visibility = View.VISIBLE
            image.setImageURI(data?.data) // handle chosen image
        }
    }

}