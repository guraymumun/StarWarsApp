package com.example.starwarsapp.domain.datasource

import com.example.starwarsapp.data.remote.dto.PeopleResponseDTO
import com.example.starwarsapp.data.remote.service.PeopleService
import retrofit2.Response

class PeopleDataSource(private val peopleService: PeopleService) {

    suspend fun getNextPeople(url: String): Response<PeopleResponseDTO> = peopleService.getNextPeople(url)
}