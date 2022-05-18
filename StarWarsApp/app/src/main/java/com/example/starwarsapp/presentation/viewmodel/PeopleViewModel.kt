package com.example.starwarsapp.presentation.ui.viewmodel

import androidx.lifecycle.*
import com.example.starwarsapp.domain.usecase.GetPeopleUseCase
import com.example.starwarsapp.domain.model.PersonModel
import com.example.starwarsapp.data.remote.util.ApiResponse
import com.example.starwarsapp.data.remote.util.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeopleViewModel(
    private val getPeopleUseCase: GetPeopleUseCase
) : BaseViewModel() {

    private var peopleSource: LiveData<ApiResponse<List<PersonModel>>> = MutableLiveData()

    private val _peopleModel = MediatorLiveData<List<PersonModel>>()
    val peopleModel: LiveData<List<PersonModel>> get() = _peopleModel

    fun submitSearch(search: String) = viewModelScope.launch {
        _peopleModel.removeSource(peopleSource)
        withContext(Dispatchers.IO) { peopleSource = getPeopleUseCase(search) }

        _peopleModel.addSource(peopleSource) { apiResponse ->
            processResponse(apiResponse)
            when(apiResponse) {
                is ApiSuccessResponse -> {
                    apiResponse.data.let { _peopleModel.value = it }
                }
            }
        }
    }
}