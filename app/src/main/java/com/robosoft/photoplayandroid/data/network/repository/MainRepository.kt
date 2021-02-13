package com.robosoft.photoplayandroid.data.network.repository

import com.robosoft.photoplayandroid.data.network.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    fun searchPhotos(query: String, itemCount: Int) =
        apiHelper.searchPhotos(query, itemPerPage = itemCount)

    fun getBannerImage() =
        apiHelper.getBannerImage()
}
