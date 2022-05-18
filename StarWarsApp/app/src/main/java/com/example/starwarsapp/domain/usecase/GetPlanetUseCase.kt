package com.example.starwarsapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.starwarsapp.domain.model.PlanetModel
import com.example.starwarsapp.domain.repository.PlanetRepository
import com.example.starwarsapp.data.remote.dto.PlanetDTO
import com.example.starwarsapp.data.remote.util.ApiResponse

class GetPlanetUseCase(
    private val repository: PlanetRepository
) : BaseUseCase<PlanetModel, PlanetDTO>() {

    suspend operator fun invoke(id: Int): LiveData<ApiResponse<PlanetModel>> {
        return processResponse(repository.getPlanet(id))
    }

    override fun mapResponse(responseApiModel: PlanetDTO?): PlanetModel = PlanetModel(
        name = responseApiModel?.name,
        gravity = responseApiModel?.gravity,
        terrain = responseApiModel?.terrain,
        population = responseApiModel?.population
    )
}