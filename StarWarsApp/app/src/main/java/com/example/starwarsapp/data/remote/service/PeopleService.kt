package com.example.starwarsapp.data.remote.service

import com.example.starwarsapp.data.remote.dto.PeopleResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {
    @GET("people")
    suspend fun getPeople(@Query("search") search: String?): Response<PeopleResponseDTO>
}