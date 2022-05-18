package com.example.starwarsapp

import com.example.starwarsapp.data.remote.dto.PeopleResponseDTO
import com.example.starwarsapp.data.remote.service.PeopleService
import retrofit2.Response

class PeopleDataSource(private val peopleService: PeopleService) {

    suspend fun getPeople(search: String?): Response<PeopleResponseDTO> = peopleService.getPeople(search)
}