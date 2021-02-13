package com.robosoft.photoplayandroid.data.di.module

import com.robosoft.photoplayandroid.data.network.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(apiHelper = get())
    }
}
