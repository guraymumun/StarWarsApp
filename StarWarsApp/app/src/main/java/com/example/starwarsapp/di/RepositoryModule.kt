package com.example.starwarsapp.di

import com.example.starwarsapp.domain.repository.PeopleRepository
import com.example.starwarsapp.domain.repository.PeopleRepositoryImpl
import com.example.starwarsapp.domain.repository.PlanetRepository
import com.example.starwarsapp.domain.repository.PlanetRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<PeopleRepository> { PeopleRepositoryImpl(get()) }
    factory<PlanetRepository> { PlanetRepositoryImpl(get()) }
}