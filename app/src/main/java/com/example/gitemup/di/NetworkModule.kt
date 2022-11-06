package com.example.gitemup.di

import com.example.gitemup.config.BASE_URL
import com.example.gitemup.config.DATETIME_ISO8601_FORMAT
import com.example.gitemup.data.network.API
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        client.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


        client.addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Cache-Control", "no-cache")
            requestBuilder.addHeader("x-lang", Locale.getDefault().language)
            val newRequest = requestBuilder.build()
            Timber.i("Request --> $newRequest")
            it.proceed(newRequest)
        }

        client.build()
    }


    single {
        val gson = GsonBuilder().setDateFormat(DATETIME_ISO8601_FORMAT).create()
        Retrofit.Builder().client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        get<Retrofit>().create(API::class.java)
    }

}
