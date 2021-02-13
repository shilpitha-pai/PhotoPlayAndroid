package com.robosoft.photoplayandroid.data.network.repository

import com.robosoft.photoplayandroid.data.network.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    fun searchPhotos(query: String) =
        apiHelper.searchPhotos(query)

    fun getBannerImage() =
        apiHelper.getBannerImage()
}
