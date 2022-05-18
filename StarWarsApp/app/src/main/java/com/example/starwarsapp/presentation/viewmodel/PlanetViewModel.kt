package com.example.starwarsapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.domain.usecase.GetPlanetUseCase
import com.example.starwarsapp.domain.model.PlanetModel
import com.example.starwarsapp.data.remote.util.ApiResponse
import com.example.starwarsapp.data.remote.util.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanetViewModel (
    private val getPlanetUseCase: GetPlanetUseCase
) : BaseViewModel() {

    private var planetSource: LiveData<ApiResponse<PlanetModel>> = MutableLiveData()

    private val _planetModel = MediatorLiveData<PlanetModel>()
    val planetModel: LiveData<PlanetModel> get() = _planetModel

    private val _close = MutableLiveData<Boolean>()
    val close: MutableLiveData<Boolean> get() = _close

    fun getPlanet(id: Int) = viewModelScope.launch {
        _planetModel.removeSource(planetSource)
        withContext(Dispatchers.IO) { planetSource = getPlanetUseCase(id) }

        _planetModel.addSource(planetSource) { apiResponse ->
            processResponse(apiResponse)
            when(apiResponse) {
                is ApiSuccessResponse -> {
                    apiResponse.data.let { _planetModel.value = it }
                }
            }
        }
    }

    fun close() {
        _close.value = true
    }
}