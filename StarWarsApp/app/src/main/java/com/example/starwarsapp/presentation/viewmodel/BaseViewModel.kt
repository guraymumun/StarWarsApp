package com.example.starwarsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapp.data.remote.util.*

abstract class BaseViewModel : ViewModel() {

    private val _progressVisible = MediatorLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _noInternet = MediatorLiveData<Boolean>()
    val noInternet: LiveData<Boolean> get() = _noInternet

    private val _errorMessageResId = MediatorLiveData<Int>()
    val errorMessageResId: LiveData<Int> get() = _errorMessageResId

    private val _errorMessage = MediatorLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        _progressVisible.value = false
    }

    fun setProgressVisible(visible: Boolean) {
        _progressVisible.value = visible
    }

    fun showError(messageResId: Int) {
        _errorMessageResId.value = messageResId
    }

    fun showError(message: String) {
        _errorMessage.value = message
    }

    fun <T> processResponse(response: ApiResponse<T>) {
        when (response) {
            is ApiSuccessResponse -> {
                setProgressVisible(false)
                if (response.message.isNotEmpty())
                    showError(response.message)
            }

            is ApiErrorResponse -> {
                setProgressVisible(false)
                when (response.error) {
                    is ApiError.Unknown -> {
                        _noInternet.postValue(true)
                    }

                    else -> {
                        _noInternet.postValue(true)
                    }
                }
            }

            is InProgress -> {
                setProgressVisible(true)
            }
        }
    }
}