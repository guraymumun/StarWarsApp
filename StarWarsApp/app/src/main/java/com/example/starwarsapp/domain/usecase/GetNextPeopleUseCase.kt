package com.example.starwarsapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.starwarsapp.domain.model.PersonModel
import com.example.starwarsapp.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNextPeopleUseCase(private val repository: PeopleRepository) {

    operator fun invoke(search: String): Flow<PagingData<PersonModel>> {
        return repository.getNextPeople(search).map { mappingData ->
            mappingData.map {
                PersonModel(it.name, it.homeworld)
            }
        }
    }
}