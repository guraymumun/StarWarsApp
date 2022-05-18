package com.example.starwarsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.starwarsapp.data.remote.dto.PlanetDTO
import com.example.starwarsapp.data.remote.util.ApiResponse
import com.example.starwarsapp.data.remote.util.NetworkBoundResource
import com.example.starwarsapp.domain.datasource.PlanetDataSource
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