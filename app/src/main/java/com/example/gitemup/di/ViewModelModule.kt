package com.example.gitemup.di

import com.example.gitemup.viewmodels.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RepositoryViewModel(get()) }
}