package com.robosoft.photoplayandroid.data.network.api

import com.robosoft.photoplayandroid.data.model.PhotoResults
import io.reactivex.Single
import retrofit2.Response

class ApiHelper(private val apiService: ApiService) {

    fun searchPhotos(query: String, itemPerPage: Int): Single<Response<PhotoResults>> =
        apiService.searchPhotos(query = query, itemPerPage)

    fun getBannerImage(): Single<Response<PhotoResults>> = apiService.getBannerImage()
}
