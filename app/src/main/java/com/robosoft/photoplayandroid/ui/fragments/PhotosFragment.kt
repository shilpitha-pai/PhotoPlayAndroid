package com.robosoft.photoplayandroid.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class PhotosFragment : BaseFragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private lateinit var adapter: PhotosAdapter
    private var pageSize = INITIAL_PAGE_SIZE

    override fun getLayout(): Int = R.layout.fragment_photos

    override fun setUpView(view: View) {
        setupObserver()
        mainViewModel.searchPhotos(HOME_CATEGORY, INITIAL_PAGE_SIZE)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter =
            PhotosAdapter(
                screenSize = ScreenUtils.getScreenType(requireContext()),
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
        recyclerView.addOnScrollListener(recyclerPageScrollListener)
    }


    private fun setupObserver() {
        mainViewModel.images.observe(viewLifecycleOwner) {
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
        mainViewModel.networkError.observe(this, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

    }

    private fun renderList(photoResults: PhotoResults) {
        photoResults.photos.let { adapter.addData(it) }
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
            favouriteViewModel.favouritePhoto(photo)
            Toast.makeText(
                activity,
                "Added to favorites",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val recyclerPageScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            pageSize += INITIAL_PAGE_SIZE
            mainViewModel.searchPhotos(HOME_CATEGORY, pageSize)
        }
    }

    companion object {
        const val INITIAL_PAGE_SIZE = 20
        const val HOME_CATEGORY = "animals"
    }
}
