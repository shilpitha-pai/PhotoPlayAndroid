package com.robosoft.photoplayandroid.data.network.api

import com.robosoft.photoplayandroid.data.model.PhotoResults
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(AUTH_KEY)
    @GET("search")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = DEFAULT_PAGE_COUNT,
        @Query("locale") Locale: String = LOCALE
    ): Single<Response<PhotoResults>>

    companion object {
        const val LOCALE = "en-US"
        const val DEFAULT_PAGE_COUNT = 20
        const val AUTH_KEY =
            "Authorization: 563492ad6f91700001000001459e81db4a73411bb6a504c58834d9a9"

    }
}
