package com.example.starwarsapp.data.remote.service

import com.example.starwarsapp.data.remote.dto.PlanetDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetService {
    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id: Int): Response<PlanetDTO>
}