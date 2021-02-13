package com.robosoft.photoplayandroid.data.di.module

import com.robosoft.photoplayandroid.ui.viewModels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(mainRepository = get(), networkHelper = get())
    }
}
