package com.example.starwarsapp.data.remote.util

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> : KoinComponent {

    private val result = MediatorLiveData<ApiResponse<ResultType>>()
    private val supervisorJob = SupervisorJob()

    private val gson: Gson by inject()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) { result.value =
            ApiResponse.loading()
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                fetchFromNetwork()
            } catch (httpException: HttpException) {
                val body = httpException.response()?.errorBody()
                body?.let {
                    try {
                        val model = gson.fromJson(it.string(), Response::class.java)
                        if(model != null)
                            setValue(ApiResponse.error(ApiError.getError(httpException), "", ""))
                        else
                            setValue(ApiResponse.error(ApiError.Unknown, "", ""))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        setValue(ApiResponse.error(ApiError.Unknown, "", e.message.orEmpty()))
                    }
                }
            } catch (error: Exception) {
                error.printStackTrace()
                setValue(ApiResponse.error(ApiError.Unknown, "", error.message ?: ""))
            }
        }

        return this
    }

    fun asLiveData() = result as LiveData<ApiResponse<ResultType>>

    private suspend fun fetchFromNetwork() {
        val response = createCallAsync()
        val apiResponse = ApiResponse.success(
            response.body()
        )
        setValue(apiResponse)
    }

    @MainThread
    private fun setValue(newValue: ApiResponse<ResultType>) {
        if (result.value != newValue)
            result.postValue(newValue)
    }

    @MainThread
    protected abstract suspend fun createCallAsync(): Response<ResultType>
}