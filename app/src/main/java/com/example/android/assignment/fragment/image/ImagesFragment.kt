package com.example.android.assignment.fragment.image

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.assignment.R


class ImagesFragment : Fragment() {

    private val RESULT_LOAD_IMAGE = 1
    private lateinit var galleryBtn: Button
    private lateinit var image: ImageView

    private lateinit var model : ImageViewModel

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

        model = ViewModelProviders.of(this).get(ImageViewModel::class.java)

        model.isChanged.observe(viewLifecycleOwner, Observer { hasChanged ->
            if (hasChanged){
                displayImage(model.data)
            }
        })

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
            model.setData(data)
            displayImage(model.data)
        }
    }

    fun displayImage(data: LiveData<Intent?>){
        galleryBtn.visibility = View.GONE

        image.visibility = View.VISIBLE
        image.setImageURI(model.data.value?.data) // handle chosen image
    }

}