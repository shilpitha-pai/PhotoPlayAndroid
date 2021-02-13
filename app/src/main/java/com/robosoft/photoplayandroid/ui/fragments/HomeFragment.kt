package com.robosoft.photoplayandroid.ui.fragments

import android.view.View
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val TAG = "BASICS"

    override fun getLayout(): Int = R.layout.fragment_home

    override fun setUpView(view: View) {
        loadPhotosFragment()
        photos.setOnClickListener {
            loadPhotosFragment()
        }

        videos.setOnClickListener {
            loadVideosFragment()
        }
    }

    private fun loadPhotosFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, PhotosFragment())
            .commit()
    }

    private fun loadVideosFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, VideosFragment())
            .commit()
    }
}
