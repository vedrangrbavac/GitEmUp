package com.example.gitemup.common.base

import com.example.gitemup.common.errors.ErrorHandler
import com.example.gitemup.data.network.API
import retrofit2.Response

abstract class BaseService(open val api: API) {

    suspend fun <T> apiRequest(
        errorResolver: ErrorHandler.ErrorResolver? = null,
        call: suspend () -> Response<T>
    ): T {

        val response = call()

        return if (response.isSuccessful) {
            response.body() ?: throw KotlinNullPointerException()
        } else {
            throw ErrorHandler.resolveNetworkError(response, errorResolver)
        }
    }

}
