package com.example.gitemup.common.errors


import com.example.gitemup.R
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {

    fun <T> resolveNetworkError(response: Response<T>, customErrorResolver: ErrorResolver? = null): Exception {
        return customErrorResolver?.let {
            customErrorResolver.resolve(response)
        } ?: DefaultErrorResolver.resolve(response)
    }

    fun resolveExceptionMessageId(error: Throwable): Int = when (error) {
        is UnknownHostException -> R.string.network_error
        is SocketTimeoutException -> R.string.timeout_error
        is ServerError -> R.string.server_error
        is UnauthorizedError -> R.string.unauthorized_error
        is BadRequestError -> R.string.bad_request_error
        is NotFoundError -> R.string.not_found_error
        is MethodNotAllowedError -> R.string.not_allowed_error
        is TimeoutError -> R.string.timeout_error
        else -> R.string.unknown_error
    }

    interface ErrorResolver {
        fun <T> resolve(response: Response<T>): Exception
    }

    object DefaultErrorResolver : ErrorResolver {
        override fun <T> resolve(response: Response<T>): Exception =
                when (response.code()) {
                    in 500..599 -> ServerError(response.message())
                    400, 423 -> BadRequestError(response.message())
                    401 -> UnauthorizedError()
                    403 -> ForbiddenActionError()
                    404 -> NotFoundError()
                    405 -> MethodNotAllowedError()
                    408 -> TimeoutError()
                    422 -> WrongFieldError()
                    else -> Exception(response.message())
                }
    }


    class ServerError(message: String? = null) : Exception(message)
    class UnauthorizedError : Exception()
    class BadRequestError(message: String?) : Exception(message)
    class ForbiddenActionError : Exception()
    class NotFoundError : Exception()
    class MethodNotAllowedError : Exception()
    class TimeoutError : Exception()
    class WrongFieldError : Exception()

}