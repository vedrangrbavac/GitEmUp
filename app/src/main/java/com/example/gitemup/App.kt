package com.example.gitemup

import android.app.Application
import com.example.gitemup.di.networkModule
import com.example.gitemup.di.repositoryModule
import com.example.gitemup.di.serviceModule
import com.example.gitemup.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber


class App : Application() {
    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


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
