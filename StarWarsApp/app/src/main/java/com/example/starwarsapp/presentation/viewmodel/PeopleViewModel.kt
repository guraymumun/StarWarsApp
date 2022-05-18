package com.example.starwarsapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarsapp.domain.model.PersonModel
import com.example.starwarsapp.domain.usecase.GetNextPeopleUseCase
import kotlinx.coroutines.flow.Flow

class PeopleViewModel(
    private val getNextPeopleUseCase: GetNextPeopleUseCase
) : BaseViewModel() {

    fun getNextPeople(search: String): Flow<PagingData<PersonModel>> {
        return getNextPeopleUseCase(search).cachedIn(viewModelScope)
    }
}