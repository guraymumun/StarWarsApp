package com.example.starwarsapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsapp.data.remote.dto.PersonDTO
import com.example.starwarsapp.domain.datasource.PeopleDataSource
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getNextPeople(search: String): Flow<PagingData<PersonDTO>>
}

class PeopleRepositoryImpl(private val peopleDataSource: PeopleDataSource) : PeopleRepository {
    override fun getNextPeople(search: String): Flow<PagingData<PersonDTO>> =
        Pager(PagingConfig(pageSize = 15, initialLoadSize = 15),
            pagingSourceFactory = { PeoplePagingSource(peopleDataSource, search) }
        ).flow
}