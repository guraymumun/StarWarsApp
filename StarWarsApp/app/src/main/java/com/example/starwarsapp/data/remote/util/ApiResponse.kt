package com.example.starwarsapp.data.remote.util

sealed class ApiResponse<T>(
    open var status: String = "",
    open var message: String = ""
) {
    companion object {

        fun <T> loading(): ApiResponse<T> = InProgress()

        fun <T> error(apiError: ApiError, status: String, message: String): ApiErrorResponse<T> {
            return ApiErrorResponse(apiError, status, message)
        }

        fun <T> success(model: T?): ApiResponse<T> {
            return ApiSuccessResponse(model)
        }
    }
}

data class ApiSuccessResponse<T>(val data: T?) : ApiResponse<T>()

data class ApiErrorResponse<T>(var error: ApiError, override var status: String, override var message: String) : ApiResponse<T>()

class InProgress<T> : ApiResponse<T>()