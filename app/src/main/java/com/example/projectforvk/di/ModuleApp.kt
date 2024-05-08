package com.example.projectforvk.di

import com.example.projectforvk.data.network.common.Constants.apiService
import com.example.projectforvk.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


    val viewModel = module {
        viewModel {
            MainViewModel(get())
        }
    }

    val networkModule = module {
        single{
            apiService
        }
}