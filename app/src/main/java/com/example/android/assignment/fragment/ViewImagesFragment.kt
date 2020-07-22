package com.example.android.assignment.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.assignment.R
import com.example.android.assignment.adapter.HomeRecyclerAdapter
import com.example.android.assignment.model.Image
import com.example.android.assignment.services.ImageApi
import com.example.android.assignment.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class ViewImagesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: HomeRecyclerAdapter

    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_images, container, false)

        recyclerView = view.findViewById(R.id.home_recyclerview)
        layoutManager = LinearLayoutManager(activity)

        progressLayout = view.findViewById(R.id.progress_layout)
        progressBar = view.findViewById(R.id.progress_bar)
        progressLayout.visibility = View.VISIBLE

        loadImages()

    return view
}

private fun loadImages() {

    val imageService = ServiceBuilder.buildService(ImageApi::class.java)

    val requestCall = imageService.getImage()

    requestCall.enqueue(object :
        retrofit2.Callback<List<com.example.android.assignment.model.Image>> {
        override fun onFailure(
            call: Call<List<com.example.android.assignment.model.Image>>?,
            t: Throwable?
        ) {
            Toast.makeText(context, "Error occurred", Toast.LENGTH_LONG).show()
        }

        override fun onResponse(
            call: Call<List<com.example.android.assignment.model.Image>>?,
            response: Response<List<com.example.android.assignment.model.Image>>?
        ) {
            if (response != null) {

                if (response.isSuccessful) {

                    progressLayout.visibility = View.GONE
                    val resList = response.body()
                    recyclerAdapter = HomeRecyclerAdapter(activity as Context, resList)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = recyclerAdapter

                } else {
                    Toast.makeText(context, "Failed to fetch images", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(context, "Some error occurred", Toast.LENGTH_LONG).show()
            }
        }

    })

}
}