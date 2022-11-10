package com.example.gitemup.di

import com.example.gitemup.repositories.RepositoryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RepositoryRepository(get()) }
}