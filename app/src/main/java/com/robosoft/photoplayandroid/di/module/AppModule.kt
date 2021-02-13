package com.robosoft.photoplayandroid.di.module

import android.content.Context
import androidx.room.Room
import com.robosoft.photoplayandroid.BuildConfig
import com.robosoft.photoplayandroid.BuildConfig.BASE_URL
import com.robosoft.photoplayandroid.data.database.PhotosAndVideosDatabase
import com.robosoft.photoplayandroid.data.network.api.ApiHelper
import com.robosoft.photoplayandroid.data.network.api.ApiService
import com.robosoft.photoplayandroid.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    // APIHelperImpl extends APIHelper
    single { ApiHelper(apiService = get()) }

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), PhotosAndVideosDatabase::class.java, "P.db")
            .allowMainThreadQueries()
            .build()
    }
    single { get<PhotosAndVideosDatabase>().photoDao() }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
