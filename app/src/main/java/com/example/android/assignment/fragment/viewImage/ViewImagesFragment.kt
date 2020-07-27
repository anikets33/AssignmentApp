package com.example.android.assignment.fragment.viewImage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.assignment.R
import com.example.android.assignment.adapter.HomeRecyclerAdapter
import com.example.android.assignment.fragment.image.ImageViewModel
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

    private lateinit var viewModel : ViewImageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewImageViewModel::class.java)

        context?.let {
            viewModel.getImages(it).observe(viewLifecycleOwner, Observer { resList ->

                progressLayout.visibility = View.GONE

                recyclerAdapter = HomeRecyclerAdapter(activity as Context, resList)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = recyclerAdapter

            })
        }
    }

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

        return view
    }

}