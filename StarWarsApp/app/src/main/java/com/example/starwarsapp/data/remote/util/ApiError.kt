package com.example.starwarsapp.data.remote.util

import retrofit2.HttpException
import java.net.HttpURLConnection

sealed class ApiError {

    companion object {

        fun getError(httpException: HttpException) : ApiError {
            return when(httpException.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> Unauthorized
                // access denied
                HttpURLConnection.HTTP_FORBIDDEN -> AccessDenied

                HttpURLConnection.HTTP_NOT_FOUND -> NotFound

                HttpURLConnection.HTTP_GONE -> NoLongerAvailable

                // all the others will be treated as unknown error
                else -> Unknown
            }
        }
    }

    object Unauthorized : ApiError()

    object AccessDenied: ApiError()

    object NotFound: ApiError()

    object NoLongerAvailable : ApiError()

    object Unknown : ApiError()
}