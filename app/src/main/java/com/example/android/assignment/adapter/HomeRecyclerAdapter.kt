package com.example.android.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.assignment.R
import com.example.android.assignment.model.Image
import com.squareup.picasso.Picasso

class HomeRecyclerAdapter(val context: Context, private val itemList: List<Image>) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.home_image)
        val id: TextView = view.findViewById(R.id.home_id)
        val title: TextView = view.findViewById(R.id.home_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = itemList[position]

        val id = item.id.toString()
        val title = item.title

        holder.id.text = "ID  : $id"
        holder.title.text = "TITLE  : $title"
        Picasso.get().load(item.thumbnailUrl).error(R.drawable.ic_launcher_foreground).into(holder.img)

    }
}