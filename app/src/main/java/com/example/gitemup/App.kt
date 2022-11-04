package com.example.gitemup

import android.app.Application
import com.example.gitemup.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            loadKoinModules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    serviceModule,
                    networkModule
                )
            )
        }
    }
}
