package com.example.android.assignment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.assignment.fragment.contactUs.ContactUsFragment
import com.example.android.assignment.fragment.image.ImagesFragment
import com.example.android.assignment.fragment.viewImage.ViewImagesFragment

class MyAdapter(
    context: Context,
    fm: FragmentManager,
    lifecycle: Lifecycle,
    var totalTabs: Int
) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactUsFragment()
            1 -> ImagesFragment()
            2 -> ViewImagesFragment()
            else -> ContactUsFragment()
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }

}