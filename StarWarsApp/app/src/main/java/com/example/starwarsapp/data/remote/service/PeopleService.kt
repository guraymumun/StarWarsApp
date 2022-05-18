package com.example.starwarsapp.data.remote.service

import com.example.starwarsapp.data.remote.dto.PeopleResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PeopleService {
    @GET
    suspend fun getNextPeople(@Url url: String?): Response<PeopleResponseDTO>
}