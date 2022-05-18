package com.example.starwarsapp

import androidx.lifecycle.LiveData
import com.example.starwarsapp.data.remote.dto.PlanetDTO
import com.example.starwarsapp.data.remote.utils.ApiResponse
import com.example.starwarsapp.data.remote.utils.NetworkBoundResource
import retrofit2.Response


interface PlanetRepository {
    suspend fun getPlanet(id: Int): LiveData<ApiResponse<PlanetDTO>>
}

class PlanetRepositoryImpl(private val planetDataSource: PlanetDataSource) : PlanetRepository {
    override suspend fun getPlanet(id: Int): LiveData<ApiResponse<PlanetDTO>> {
        return object : NetworkBoundResource<PlanetDTO, PlanetDTO>() {
            override suspend fun createCallAsync(): Response<PlanetDTO>
                    = planetDataSource.getPlanet(id)
        }.build().asLiveData()
    }
}