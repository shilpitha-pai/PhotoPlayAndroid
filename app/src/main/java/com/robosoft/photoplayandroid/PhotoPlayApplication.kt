package com.robosoft.photoplayandroid

import android.app.Application
import android.content.Context
import com.robosoft.photoplayandroid.di.module.appModule
import com.robosoft.photoplayandroid.di.module.repoModule
import com.robosoft.photoplayandroid.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoPlayApplication : Application() {

    companion object {
        private lateinit var appContext: Context

        @JvmStatic
        fun getContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidContext(this@PhotoPlayApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}
