package com.robosoft.photoplayandroid.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.data.model.Photo
import com.robosoft.photoplayandroid.data.model.PhotoResults
import com.robosoft.photoplayandroid.ui.adapters.ACTION
import com.robosoft.photoplayandroid.ui.adapters.PhotosAdapter
import com.robosoft.photoplayandroid.ui.base.BaseFragment
import com.robosoft.photoplayandroid.ui.viewModels.FavouriteViewModel
import com.robosoft.photoplayandroid.ui.viewModels.MainViewModel
import com.robosoft.photoplayandroid.utils.ScreenUtils
import com.robosoft.photoplayandroid.utils.Status
import kotlinx.android.synthetic.main.fragment_photos.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment() {
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private val TAG = "BASICS"
    private lateinit var adapter: PhotosAdapter

    override fun getLayout(): Int = R.layout.fragment_photos

    override fun setUpView(view: View) {
        setupObserver()
        favouriteViewModel.getFavouritePhotos()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter =
            PhotosAdapter(
                screenSize = ScreenUtils.getScreenType(activity?.baseContext!!),
                itemClickListener = {
                    onItemClick(it.first, it.second)
                }
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }


    private fun setupObserver() {
        favouriteViewModel.favoritePhotos.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { photoResults ->
                        renderList(photoResults)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }

    private fun renderList(photo: List<Photo>) {
         adapter.addData(photo)
        adapter.notifyDataSetChanged()
    }


    private fun onItemClick(action: ACTION, photo: Photo) {
        if (action == ACTION.CARD_CLICK) {
            Toast.makeText(
                activity,
                "Added ${action}  ${photo.photographer_url} to .....",
                Toast.LENGTH_LONG
            ).show()
        } else {
            favouriteViewModel.deletePhoto(photo)
            adapter.delete(photo)
            adapter.notifyDataSetChanged()
            Toast.makeText(
                activity,
                "Added to favorites",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
