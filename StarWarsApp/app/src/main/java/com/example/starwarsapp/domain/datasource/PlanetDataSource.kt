package com.example.starwarsapp.domain.datasource

import com.example.starwarsapp.data.remote.dto.PlanetDTO
import com.example.starwarsapp.data.remote.service.PlanetService
import retrofit2.Response

class PlanetDataSource(private val planetService: PlanetService) {

    suspend fun getPlanet(id: Int): Response<PlanetDTO> = planetService.getPlanet(id)
}