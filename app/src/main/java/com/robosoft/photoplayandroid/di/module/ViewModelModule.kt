package com.robosoft.photoplayandroid.di.module

import com.robosoft.photoplayandroid.ui.viewModels.BannerViewModel
import com.robosoft.photoplayandroid.ui.viewModels.FavouriteViewModel
import com.robosoft.photoplayandroid.ui.viewModels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(mainRepository = get(), networkHelper = get())
    }
    viewModel {
        FavouriteViewModel(photoVideoDbRepository = get())
    }
    viewModel {
        BannerViewModel(mainRepository = get(),networkHelper = get())
    }
}
