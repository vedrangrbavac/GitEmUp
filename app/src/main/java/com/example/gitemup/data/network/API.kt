package com.example.gitemup.data.network

import com.example.gitemup.data.models.domain.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("repositories")
    suspend fun getRepositories(@Query("q" , encoded = true) query: String) : Response<ResponseWrapper>
}