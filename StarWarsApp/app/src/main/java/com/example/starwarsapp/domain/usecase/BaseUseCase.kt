package com.example.starwarsapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.starwarsapp.data.remote.util.ApiErrorResponse
import com.example.starwarsapp.data.remote.util.ApiResponse
import com.example.starwarsapp.data.remote.util.ApiSuccessResponse
import com.example.starwarsapp.data.remote.util.InProgress
import java.lang.Exception

abstract class BaseUseCase<ResponseModel, ResponseApiModel> {

    fun processResponse(response: LiveData<ApiResponse<ResponseApiModel>>) : LiveData<ApiResponse<ResponseModel>> {
        return Transformations.map(response) {
            when (it) {
                is ApiSuccessResponse -> ApiResponse.success(mapResponse(it.data))

                is InProgress -> ApiResponse.loading()

                is ApiErrorResponse -> ApiResponse.error(it.error, it.status, it.message)

                else -> throw Exception("Unknown api state")
            }
        }
    }

    abstract fun mapResponse(responseApiModel: ResponseApiModel?) : ResponseModel?
}