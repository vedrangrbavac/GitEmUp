package com.example.gitemup.di

import com.example.gitemup.data.network.service.RepositoryService
import org.koin.dsl.module

val serviceModule = module {
    single { RepositoryService(get()) }
}