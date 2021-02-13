package com.robosoft.photoplayandroid.ui.fragments

import android.view.View
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_home

    override fun setUpView(view: View) {
        loadPhotosFragment()
        photos.setOnClickListener {
            loadPhotosFragment()
        }

        videos.setOnClickListener {
            loadVideosFragment()
        }

        favorite.setOnClickListener {
            loadFavoritesFragment()
        }
    }

    private fun loadPhotosFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, PhotosFragment())
            .commit()
    }

    private fun loadVideosFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, VideosFragment())
            .commit()
    }

    private fun loadFavoritesFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, FavoritesFragment())
            .commit()
    }
}
