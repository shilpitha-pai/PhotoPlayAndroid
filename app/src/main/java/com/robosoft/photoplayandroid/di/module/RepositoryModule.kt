package com.robosoft.photoplayandroid.di.module

import com.robosoft.photoplayandroid.data.database.PhotoVideoDbRepository
import com.robosoft.photoplayandroid.data.network.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(apiHelper = get())
    }
    single {
        PhotoVideoDbRepository(photosDao = get())
    }
}
