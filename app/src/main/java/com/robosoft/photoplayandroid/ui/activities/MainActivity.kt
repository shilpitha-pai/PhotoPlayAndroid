package com.robosoft.photoplayandroid.ui.activities

import android.os.Bundle
import android.util.Log
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseActivity
import com.robosoft.photoplayandroid.ui.viewModels.MainViewModel
import com.robosoft.photoplayandroid.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val TAG = "BASICS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        mainViewModel.searchPhotos("animals")
    }

    override fun getLayout(): Int = R.layout.activity_main


    private fun setupObserver() {
        mainViewModel.images.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { images ->
                        Log.d(TAG, "setupObserver: ${images.photos.toString()}")
                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "setupObserver: Loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "setupObserver: ${it.message}")
                }
            }
        }
    }
}